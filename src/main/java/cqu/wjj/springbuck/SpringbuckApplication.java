package cqu.wjj.springbuck;

import cqu.wjj.springbuck.converter.MoneyReadConverter;
import cqu.wjj.springbuck.model.Coffee;
import cqu.wjj.springbuck.service.CoffeeService;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class SpringbuckApplication implements ApplicationRunner {

	@Autowired
	private CoffeeService coffeeService;


	@Autowired
	private MongoTemplate mongoTemplate;



	public static void main(String[] args) {
		SpringApplication.run(SpringbuckApplication.class, args);
	}


	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));

	}

	@Bean
	public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Coffee> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);

		return template;
	}


	@Override
	public void run(ApplicationArguments args) throws Exception{
		Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date()).build();

		Coffee saved = mongoTemplate.save(espresso);
		log.info("Coffee {}", saved);


		Optional<Coffee> f1=coffeeService.findOneCoffee("espresso");


		Thread.sleep(500);
		Optional<Coffee> f2=coffeeService.findOneCoffee("espresso");

		Thread.sleep(5000);
		Optional<Coffee> f3=coffeeService.findOneCoffee("espresso");







	}


}
