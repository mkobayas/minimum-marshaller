Minimum Marshaller
==================

Minimum marshaller is a very high performance and high density marshaller licensed under Apache License 2.0.  

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

N to 1 referenced object will be unmarshalled to N to N referenced object.  

## Performance comparison

### Single thread performance ( [see](https://github.com/mkobayas/jvm-serializers) )

Serializers (no shared refs)  
Benchmarks serializers  
Only cycle free tree structures. An object referenced twice will be serialized twice.  
no manual optimizations.  
schema is known in advance (pre registration or even class generation). (Not all might make use of that)  


Ser Time+Deser Time (ns)

<img src="http://goo.gl/bR3pyZ"/>


Size, Compressed [light] in bytes

<img src="http://goo.gl/9GgIe8"/>

```
                                   create     ser   deser   total   size  +dfl
avro-generic                          398    1980    1199    3179    221   133
avro-specific                         100    1749    1466    3215    221   133
bson/jackson/databind                  68    5508    6505   12013    506   286
bson/mongodb/manual                    68    3556    8167   11723    495   278
cbor/jackson/databind                  67    2216    2070    4286    397   246
cbor/jackson/manual                    67    1739    1342    3081    386   238
fst-flat-pre                           68     712     773    1485    251   165
fst-flat                               67    1029    1183    2212    314   204
fst                                    67    1609    1531    3140    316   203
hessian                                65    3996    5783    9779    501   313
java-built-in                          68    5404   26248   31651    889   514
java-built-in-serializer               69    5490   25759   31249    889   514
java-manual                            67     930     676    1606    255   147
jboss-marshalling-river-ct-manual      67    1770    1276    3046    289   167
jboss-marshalling-river-ct             68    3194    2290    5484    298   199
jboss-marshalling-river-manual         68    2327    4212    6539    483   240
jboss-marshalling-river                68    4733   18052   22784    694   400
jboss-marshalling-serial               68   11162   24588   35751    856   498
jboss-serialization                    67    6566    6597   13164    932   582
json/argo/manual-tree                  66   69192   15298   84490    485   263
json/fastjson/databind                 69    1320    1273    2592    486   262
json/flexjson/databind                 69   20167   27860   48028    503   273
json/gson/databind                     67    5598    4976   10574    486   259
json/gson/manual                       70    3820    4051    7871    468   253
json/gson/manual-tree                  67    5504    5862   11366    485   259
json/jackson+afterburner/databind      66    1594    1999    3593    485   261
json/jackson/databind                  67    1706    2474    4181    485   261
json/jackson-jr/databind               70    1806    2702    4509    468   255
json/jackson/manual                    68    1190    1578    2767    468   253
json/javax-stream/glassfish            68    6909   11515   18424    468   253
json/javax-tree/glassfish            1453    9540   13119   22659    485   263
json/jsonij/manual-jpath               68   29758   12493   42251    478   255
json/json-lib/databind                 66   28733  105655  134388    485   263
json/json.simple/manual                67    6033    8568   14601    495   269
json/json-smart/manual-tree            69    5971    3972    9943    495   269
json/org.json/manual-tree              68    6845    8731   15576    485   259
json/protobuf                         128    8946   55883   64829    488   253
json/protostuff-manual                 69    1414    1881    3296    449   233
json/protostuff-runtime                68    1622    2273    3894    469   243
json/svenson/databind                  67    4603   11126   15730    495   265
kryo-flat-pre                          68     669     865    1534    212   132
kryo-flat                              67     827    1126    1953    268   177
kryo-manual                            69     558     644    1202    211   131
kryo-opt                               68     691     867    1558    209   129
kryo-serializer                        67    1599    1453    3052    286   188
minimum-marshaller                     68    1204    1210    2414    288   179
msgpack-databind                       67     927    1491    2417    233   146
msgpack-manual                         66     963    1372    2334    233   146
protobuf/protostuff                    99     562     758    1320    239   149
protobuf/protostuff-runtime            67     742     876    1618    241   150
protobuf                              127    1357     752    2109    239   149
protostuff-graph                      100     791     772    1563    239   150
protostuff-graph-runtime               67     907     977    1884    241   151
protostuff-manual                      68     478     732    1210    239   150
protostuff                             99     537     741    1278    239   150
protostuff-runtime                     68     695     877    1571    241   151
scala/java-built-in                   143    8199   41129   49328   1312   700
scala/sbinary                         146    1730    1206    2936    255   147
smile/jackson+afterburner/databind     68    1447    1481    2928    352   252
smile/jackson/databind                 68    1585    1797    3382    338   241
smile/jackson/manual                   69    1059    1175    2234    341   244
stephenerialization                    48    6217   26418   32635   1093   517
thrift-compact                        121    1545     952    2496    240   148
thrift                                120    1882    1053    2935    349   197
wobly-compact                          41    1016     613    1629    225   139
wobly                                  41     989     570    1559    251   151
xml/aalto-manual                       66    2202    3073    5274    653   304
xml/exi-manual                         66   16633   14525   31158    337   327
xml/fastinfo-manual                    67    6500    5670   12170    377   284
xml/jackson/databind                   67    2881    5274    8155    683   286
xml/javolution/manual                  68    4865    7635   12501    504   263
xml/woodstox-manual                    67    3278    4934    8212    653   304
xml/xstream+c-aalto                    68    4346    9805   14152    525   273
xml/xstream+c-fastinfo                 68    7931    8738   16669    345   264
xml/xstream+c                          68    5703   13564   19267    487   244
xml/xstream+c-woodstox                 67    5352   11855   17207    525   273
yaml/jackson/databind                  69   20646   30754   51400    505   260
```

### Multi thread performance ( [see](https://github.com/mkobayas/minimum-marshaller-benchmark) )


Ser and Deser per second

<img src="http://goo.gl/NErSZA"/>

```
Benchmark                                           Mode   Samples        Score  Score error    Units
o.m.m.b.KryoBench.t1_marshalling                   thrpt         2    30164.715          NaN    ops/s
o.m.m.b.KryoBench.t2_unmarshalling                 thrpt         2    24577.007          NaN    ops/s
o.m.m.b.KryoBench.t3_mix                           thrpt         2    23613.027          NaN    ops/s
o.m.m.b.KryoThreadLocalBench.t1_marshalling        thrpt         2  1350733.807          NaN    ops/s
o.m.m.b.KryoThreadLocalBench.t2_unmarshalling      thrpt         2   772404.799          NaN    ops/s
o.m.m.b.KryoThreadLocalBench.t3_mix                thrpt         2   607723.728          NaN    ops/s
o.m.m.b.MessagePackBench.t1_marshalling            thrpt         2   963043.104          NaN    ops/s
o.m.m.b.MessagePackBench.t2_unmarshalling          thrpt         2   575063.274          NaN    ops/s
o.m.m.b.MessagePackBench.t3_mix                    thrpt         2   353768.814          NaN    ops/s
o.m.m.b.MinimumMarshallerBench.t1_marshalling      thrpt         2   893615.846          NaN    ops/s
o.m.m.b.MinimumMarshallerBench.t2_unmarshalling    thrpt         2   771744.119          NaN    ops/s
o.m.m.b.MinimumMarshallerBench.t3_mix              thrpt         2   402507.300          NaN    ops/s
```

## Binary size comparison

todo


