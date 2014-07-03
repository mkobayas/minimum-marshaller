/*
 * Copyright 2014 Masazumi Kobayashi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mk300.marshal.minimum.registry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class ConfigReader {

	
	public List<String> read(InputStream is, List<ConfigElement> list) {
		
		try {
			
			// 重複チェック用
			Set<Short> idSet = new HashSet<Short>();
			Set<String> classNameSet = new HashSet<String>();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			
			NodeList mappingList = doc.getElementsByTagName("class-mapping");
			
			for(int i=0; i<mappingList.getLength(); i++) {
				short id = 0;
				String className = null;
				String handlerName = null;
				
				Node node = mappingList.item(i);
				
				NamedNodeMap attributes = node.getAttributes();
				
				Node idNode = attributes.getNamedItem("id");
				if( idNode == null) {
					throw new RuntimeException("ConfingReaderで例外：　" + (i+1) + "番目のclass-mappingにid属性が有りません.");
				}
				int idInt = Integer.parseInt(idNode.getTextContent());
				if( idInt > 65535 || idInt < 0) {
					throw new RuntimeException("ConfingReaderで例外：　" + (i+1) + "番目のclass-mappingのid属性の範囲が不正です. idはUnsignedShortである必要があります.");	
				}
				id = (short)idInt;
				if(id == 0) {
					throw new RuntimeException("ConfingReaderで例外：　" + (i+1) + "番目のclass-mappingのid属性で0は利用できません.");
				}
				if( idSet.contains(id) ) {
					throw new RuntimeException("ConfingReaderで例外：　" + (i+1) + "番目のclass-mappingのid属性は重複定義です.");
				} else {
					idSet.add(id);
				}
				
				Node classNode = attributes.getNamedItem("class");
				if( classNode == null) {
					throw new RuntimeException("ConfingReaderで例外：　" + (i+1) + "番目のclass-mappingにclass属性が有りません.");
				}
				className = classNode.getTextContent();
				if( classNameSet.contains(className) ) {
					throw new RuntimeException("ConfingReaderで例外：　" + (i+1) + "番目のclass-mappingのclass属性は重複定義です.");	
				} else {
					classNameSet.add(className);
				}
				
				Node handlerNode = attributes.getNamedItem("handler");
				if(handlerNode != null) {
					handlerName = handlerNode.getTextContent();
				} else {
					handlerName = org.mk300.marshal.minimum.handler.ObjectHandler.class.getName(); 
				}
				
				ConfigElement configElement = new ConfigElement();
				configElement.setId(id);
				configElement.setClassName(className);
				configElement.setHandlerName(handlerName);
				
				list.add(configElement);
				
			}
			
			
			// 追加コンフィグ取得
			List<String> additinalConfig = new ArrayList<String>();
			
			NodeList additinalConfigNodeList = doc.getElementsByTagName("additional-config");
			for(int i=0; i<additinalConfigNodeList.getLength(); i++) {
				Node node = additinalConfigNodeList.item(i);
				NamedNodeMap attributes = node.getAttributes();
				
				Node configFileNode = attributes.getNamedItem("config-file");
				if( configFileNode == null) {
					throw new RuntimeException("ConfingReaderで例外：　additional-configの" + (i+1) + "番目にconfig-file属性が有りません.");
				}
				additinalConfig.add(configFileNode.getTextContent());
			}
			
			return additinalConfig;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
