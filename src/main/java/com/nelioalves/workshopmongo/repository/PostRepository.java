package com.nelioalves.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nelioalves.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//metado personalizado
	@Query("{ 'title': { $regex: ?0 /pattern/, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);

}
