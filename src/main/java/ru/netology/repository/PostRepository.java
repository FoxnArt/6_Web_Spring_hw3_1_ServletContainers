package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


// Stub
@Repository
public class PostRepository {

  private final ConcurrentHashMap<Integer, String> storage = new ConcurrentHashMap<>();
  private final AtomicLong counter = new AtomicLong(1);


  public List<Post> all() {
    List<Post> list = new ArrayList<>();
    Iterator<Integer> iterator = storage.keySet().iterator();
    while (iterator.hasNext()) {
      Integer i = iterator.next();
      Post post = new Post(i, storage.get(i));
      list.add(post);
    }
    return list;
  }

  public Optional<Post> getById(long id) {
    if (storage.containsKey((int)id)) {
        return Optional.of(new Post(id, storage.get((int)id)));
      }
    return Optional.empty();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      storage.put((int) counter.get(), post.getContent());
      Post resultSave = new Post(counter.get(), post.getContent());
      counter.getAndIncrement();
      return resultSave;
    } else {
      if (storage.containsKey((int)post.getId())) {
        storage.put((int)post.getId(), post.getContent());
        return post;
      }
    }
    return post;
  }

  public void removeById(long id) {
    storage.remove((int)id);
  }
}
