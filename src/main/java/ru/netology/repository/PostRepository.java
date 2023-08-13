package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


// Stub
public class PostRepository {

  private static final ConcurrentHashMap<Integer, String> STORAGE = new ConcurrentHashMap<>();
  private static final AtomicLong COUNTER = new AtomicLong(1);


  public List<Post> all() {
    List<Post> list = new ArrayList<>();
    Iterator<Integer> iterator = STORAGE.keySet().iterator();
    while (iterator.hasNext()) {
      Integer i = iterator.next();
      Post post = new Post(i, STORAGE.get(i));
      list.add(post);
    }
    return list;
  }

  public Optional<Post> getById(long id) {
    if (STORAGE.containsKey((int)id)) {
        return Optional.of(new Post(id, STORAGE.get((int)id)));
      }
    return Optional.empty();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      STORAGE.put((int)COUNTER.get(), post.getContent());
      Post resultSave = new Post(COUNTER.get(), post.getContent());
      COUNTER.getAndIncrement();
      return resultSave;
    } else {
      if (STORAGE.containsKey((int)post.getId())) {
        STORAGE.put((int)post.getId(), post.getContent());
        return post;
      }
    }
    return post;
  }

  public void removeById(long id) {
    STORAGE.remove((int)id);
  }
}
