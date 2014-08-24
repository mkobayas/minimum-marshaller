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

User Pojo Class (defined in marshaller-config.xml), and followings

```
java.lang.Enum
    
java.lang.String
java.util.Date
java.math.BigDecimal
java.math.BigInteger
java.sql.Date
java.sql.Time
java.sql.Timestamp
java.util.concurrent.atomic.AtomicInteger
java.util.concurrent.atomic.AtomicLong
java.util.concurrent.atomic.AtomicBoolean
java.util.concurrent.atomic.AtomicReference
java.util.concurrent.atomic.AtomicStampedReference

java.util.concurrent.atomic.AtomicIntegerArray
java.util.concurrent.atomic.AtomicLongArray
java.util.concurrent.atomic.AtomicReferenceArray


java.util.ArrayList
java.util.LinkedList
java.util.concurrent.CopyOnWriteArrayList
java.util.Vector
java.util.HashSet
java.util.concurrent.CopyOnWriteArraySet
java.util.LinkedHashSet
java.util.ArrayDeque
java.util.concurrent.ConcurrentLinkedDeque
java.util.concurrent.ConcurrentLinkedQueue
java.util.concurrent.LinkedTransferQueue
java.util.Stack

java.util.TreeSet
java.util.concurrent.ConcurrentSkipListSet
java.util.concurrent.ArrayBlockingQueue
java.util.concurrent.LinkedBlockingDeque
java.util.concurrent.LinkedBlockingQueue
java.util.PriorityQueue
java.util.concurrent.PriorityBlockingQueue
java.util.concurrent.SynchronousQueue

java.util.EnumSet
java.util.EnumMap

java.util.HashMap
java.util.concurrent.ConcurrentHashMap
java.util.IdentityHashMap
java.util.Properties
java.util.LinkedHashMap
java.util.TreeMap
java.util.concurrent.ConcurrentSkipListMap

java.lang.Integer
java.lang.Long
java.lang.Short
java.lang.Boolean
java.lang.Double
java.lang.Float
java.lang.Character
java.lang.Byte

boolean[]
short[]
int[]
long[]
boolean[]
float[]
double[]
char[]
Object[]
String[]
```

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

<img src="http://goo.gl/LLb7r7"/>


Size, Compressed [light] in bytes

<img src="http://goo.gl/9GgIe8"/>

```
                                   create     ser   deser   total   size  +dfl
avro-generic                          405    1992    1188    3180    221   133
avro-specific                         101    1757    1446    3202    221   133
bson/jackson/databind                  68    5553    6542   12095    506   286
bson/mongodb/manual                    69    3636    8169   11805    495   278
cbor/jackson/databind                  67    2243    2020    4263    397   246
cbor/jackson/manual                    67    1745    1341    3086    386   238
fst-flat-pre                           69     725     780    1504    251   165
fst-flat                               68    1004    1189    2194    314   204
fst                                    69    1613    1530    3142    316   203
hessian                                68    4059    5810    9869    501   313
java-built-in                          68    5474   26020   31494    889   514
java-built-in-serializer               67    5540   26638   32178    889   514
java-manual                            67     982     695    1677    255   147
jboss-marshalling-river-ct-manual      67    1801    1265    3066    289   167
jboss-marshalling-river-ct             70    3181    2176    5357    298   199
jboss-marshalling-river-manual         67    2284    4431    6714    483   240
jboss-marshalling-river                67    4762   20166   24928    694   400
jboss-marshalling-serial               69   11171   24295   35466    856   498
jboss-serialization                    68    6692    6249   12941    932   582
json/argo/manual-tree                  70   71406   15175   86582    485   263
json/fastjson/databind                 68    1271    1255    2526    486   262
json/flexjson/databind                 69   20078   28515   48593    503   273
json/gson/databind                     69    5582    5027   10610    486   259
json/gson/manual                       69    3758    4108    7866    468   253
json/gson/manual-tree                  67    5538    5920   11458    485   259
json/jackson+afterburner/databind      68    1611    1977    3588    485   261
json/jackson/databind                  68    1787    2382    4169    485   261
json/jackson-jr/databind               69    1816    2715    4530    468   255
json/jackson/manual                    68    1222    1586    2808    468   253
json/javax-stream/glassfish            67    6855   11542   18397    468   253
json/javax-tree/glassfish            1465    9665   13019   22684    485   263
json/jsonij/manual-jpath               67   29949   12634   42583    478   254
json/json-lib/databind                 68   27847  107739  135586    485   263
json/json.simple/manual                68    6210    8785   14995    495   269
json/json-smart/manual-tree            68    5974    4078   10052    495   269
json/org.json/manual-tree              68    6835    8736   15571    485   259
json/protobuf                         127    9131   55742   64873    488   253
json/protostuff-manual                 68    1434    1958    3392    449   233
json/protostuff-runtime                71    1632    2198    3830    469   243
json/svenson/databind                  70    4657   11165   15821    495   271
kryo-flat-pre                          72     672     837    1509    212   132
kryo-flat                              69     841    1123    1964    268   177
kryo-manual                            69     566     650    1216    211   131
kryo-opt                               70     673     871    1544    209   129
kryo-serializer                        66    1709    1425    3134    286   188
minimum-marshaller                     67     843     812    1655    288   179
msgpack-databind                       67     928    1486    2414    233   146
msgpack-manual                         69     953    1382    2335    233   146
protobuf/protostuff                    99     560     761    1321    239   149
protobuf/protostuff-runtime            68     752     863    1614    241   150
protobuf                              125    1368     791    2159    239   149
protostuff-graph                      100     798     784    1582    239   150
protostuff-graph-runtime               72     904    1015    1919    241   151
protostuff-manual                      67     494     736    1230    239   150
protostuff                            100     537     743    1280    239   150
protostuff-runtime                     67     703     911    1614    241   151
scala/java-built-in                   154    8228   40499   48727   1312   700
scala/sbinary                         141    1752    1207    2959    255   147
smile/jackson+afterburner/databind     68    1393    1491    2883    352   252
smile/jackson/databind                 66    1601    1852    3453    338   241
smile/jackson/manual                   67    1111    1163    2275    341   244
stephenerialization                    49    6021   27028   33049   1093   517
thrift-compact                        121    1550     922    2471    240   148
thrift                                121    1902    1063    2965    349   197
wobly-compact                          40    1022     617    1638    225   139
wobly                                  40     983     576    1559    251   151
xml/aalto-manual                       68    2174    3187    5361    653   304
xml/exi-manual                         68   16062   15065   31126    337   327
xml/fastinfo-manual                    67    6444    5688   12132    377   284
xml/jackson/databind                   69    2947    5313    8259    683   286
xml/javolution/manual                  68    4751    7366   12117    504   263
xml/woodstox-manual                    69    3264    5017    8281    653   304
xml/xstream+c-aalto                    67    4421    9808   14228    525   273
xml/xstream+c-fastinfo                 67    8054    8779   16833    345   264
xml/xstream+c                          68    5836   13843   19679    487   244
xml/xstream+c-woodstox                 70    5350   11900   17250    525   273
yaml/jackson/databind                  68   20363   30370   50733    505   260
```

### Multi thread performance ( [see](https://github.com/mkobayas/minimum-marshaller-benchmark) )


Ser and Deser per second

<img src="http://goo.gl/x0bMb5"/>

```
Benchmark                                           Mode   Samples        Score  Score error    Units
o.m.m.b.KryoBench.t1_marshalling                   thrpt         2   126734.829          NaN    ops/s
o.m.m.b.KryoBench.t2_unmarshalling                 thrpt         2    40421.264          NaN    ops/s
o.m.m.b.KryoBench.t3_mix                           thrpt         2    34864.697          NaN    ops/s
o.m.m.b.KryoThreadLocalBench.t1_marshalling        thrpt         2  5687542.725          NaN    ops/s
o.m.m.b.KryoThreadLocalBench.t2_unmarshalling      thrpt         2  2993663.644          NaN    ops/s
o.m.m.b.KryoThreadLocalBench.t3_mix                thrpt         2  2397184.197          NaN    ops/s
o.m.m.b.MessagePackBench.t1_marshalling            thrpt         2  3620089.799          NaN    ops/s
o.m.m.b.MessagePackBench.t2_unmarshalling          thrpt         2  2462151.449          NaN    ops/s
o.m.m.b.MessagePackBench.t3_mix                    thrpt         2  1479946.532          NaN    ops/s
o.m.m.b.MinimumMarshallerBench.t1_marshalling      thrpt         2  4908116.999          NaN    ops/s
o.m.m.b.MinimumMarshallerBench.t2_unmarshalling    thrpt         2  5099147.163          NaN    ops/s
o.m.m.b.MinimumMarshallerBench.t3_mix              thrpt         2  2488325.064          NaN    ops/s
```


