# REPL for Moleculer

Java REPL (Interactive Developer Console) for [Moleculer](https://moleculer-java.github.io/moleculer-java/).

## Download

**Maven**

```xml
<dependencies>
	<dependency>
		<groupId>com.github.berkesa</groupId>
		<artifactId>moleculer-java-repl</artifactId>
		<version>1.0.2</version>
		<scope>runtime</scope>
	</dependency>
</dependencies>
```

**Gradle**

```gradle
dependencies {
	compile group: 'com.github.berkesa', name: 'moleculer-java-repl', version: '1.0.2' 
}
```

## Usage from code

```java
// Create Service Broker
ServiceBroker broker = new ServiceBroker();
broker.start();

// Switch to REPL mode
broker.repl();
```

## Usage with Spring Framework

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="...">

	<!-- PACKAGE OF YOUR MOLECULER SERVICES -->

	<context:component-scan base-package="package.of.my.services" />

	<!-- INSTALL DEVELOPER CONSOLE -->

	<bean id="$repl" class="services.moleculer.repl.LocalRepl" />

	<!-- CONFIGURE TRANSPORTER -->

	<bean id="transporter" class="services.moleculer.transporter.TcpTransporter" />

	<!-- OTHER SERVICE BROKER SETTINGS -->

	<bean id="brokerConfig" class="services.moleculer.config.ServiceBrokerConfig">
		<property name="nodeID" value="node-1" />
		<property name="transporter" ref="transporter" />
	</bean>

	<!-- CREATE SERVICE BROKER INSTANCE -->

	<bean id="broker" class="services.moleculer.ServiceBroker"
		init-method="start" destroy-method="stop">
		<constructor-arg ref="brokerConfig" />
	</bean>

	<!-- MOLECULER / SPRING INTEGRATOR -->

	<bean id="registrator" class="services.moleculer.config.SpringRegistrator" />

</beans>
```

## Screenshot

![image](docs/console-java.png)


## REPL Commands

```bash
mol $ help

Commands:

  actions [options]                           List of actions
  bench <action> [jsonParams]                 Benchmark a service
  broadcast <eventName>                       Broadcast an event
  broadcastLocal <eventName>                  Broadcast an event locally
  call <actionName> [jsonParams]              Call an action
  clear <pattern>                             Delete cached entries by pattern
  dcall <nodeID> <actionName> [jsonParams]    Direct call an action
  emit <eventName>                            Emit an event
  env                                         Lists of environment properties
  events [options]                            List of event listeners
  exit, q                                     Exit application
  find <fullClassName>                        Find a class or resource
  gc                                          Invoke garbage collector
  info                                        Information about the broker
  memory                                      Show memory usage
  nodes [options]                             List of nodes
  props                                       List of Java properties
  services [options]                          List of services
  threads                                     List of threads
```

### List nodes

```bash
mol $ nodes
```

**Options**

```
    --help                    output usage information
    --details, -d             detailed list
    --all, -a                 list all (offline) nodes
    --raw                     print service registry as JSON
    --save [filename], -a     save service registry to JSON file
```

**Output**

![image](docs/nodes.png)

**Detailed output**

![image](docs/nodes-detailed.png)

# License
moleculer-java-repl is available under the [MIT license](https://tldrlegal.com/license/mit-license).
