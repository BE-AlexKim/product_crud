package test.system.carpenstreet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class CarpenstreetApplication

fun main(args: Array<String>) {
	runApplication<CarpenstreetApplication>(*args)
}
