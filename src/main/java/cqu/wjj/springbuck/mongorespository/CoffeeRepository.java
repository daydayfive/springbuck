package cqu.wjj.springbuck.mongorespository;

import cqu.wjj.springbuck.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CoffeeRepository extends MongoRepository<Coffee,String> {
}
