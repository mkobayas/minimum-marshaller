Minimum Marshaller
==================

Minimum marshaller is a very high performance and high density marshaller licensed under Apache License 2.0.  
Minimum marshallerはApache License 2.0で提供される、非常に高い性能とバイナリ密度を実現するマーシャリングライブラリです。

## Usage

### Configure marshaller-config.xml with your marshalled class.

#### Marshalling

    byte[] bytes = MinimumMarshaller.marshal(hoge);
    
#### Unmarshalling

    Hoge hoge = MinimumMarshaller.unmarshal(bytes);

### Using your application
todo

### Compatibility old to new class field
todo

### Write custom handler
todo


## Supported Class
todo

## Limitation

Minimum marshaller unable to handle a circular referenced object tree.  
Minimum marshallerは循環参照を持っているオブジェクトツリーを扱えません。  

And N to 1 referenced object will be unmarshalled to N to N referenced object.  
また、N対1の参照があるオブジェクトツリーは、ummarshall時にN対Nのオブジェクトツリーで復元されます。  

## Performance comparison

todo

## Binary size comparison

todo


