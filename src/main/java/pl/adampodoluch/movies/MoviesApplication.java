package pl.adampodoluch.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);

//
//		String cos = "assad";
//		Optional<String> cosOptional = Optional.ofNullable(cos);
//
//		if(cosOptional.isPresent()){
//			System.out.println("tak! istnieje");
//			System.out.println(cosOptional.get());
//		}else{
//			System.out.println("nie!");
//		}


	}

}
