# Beach-Resort-App-Back-End

Java Type	CQL Type
byte	tinyint
Byte	tinyint
short	smallint
Short	smallint
boolean	boolean
Boolean	boolean
int	int
Integer	int
java.math.BigInteger	varint
long	bigint
Long	bigint
double	double
Double	double
java.math.BigDecimal	decimal
float	float
Float	float
byte[ ]	blob
Byte[ ]	blob
ByteBuffer	blob
double[ ]	frozen<list<double>>
float[ ]	frozen<list<float>>
int[ ]	frozen<list<int>>
long[ ]	frozen<list<bigint>>
java.util.Date	timestamp
com.datastax.driver.core.LocalDate	date
java.time.Instant	timestamp
java.time.LocalDate	date
java.time.LocalTime	time
java.time.ZonedDateTime	tuple<timstamp, varchar>
com.datastax.driver.core.Duration	duration
java.net.InetAddress	inet
String	text
java.util.UUID	uuid
info.archinnov.achilles.type.tuples.Tuple1 ... Tuple10	tuple<...>
java.util.List<X>	list<X>
java.util.Set<X>	set<X>
java.util.Map<X,Y>	map<X,Y>
java.util.Optional<X>	X
