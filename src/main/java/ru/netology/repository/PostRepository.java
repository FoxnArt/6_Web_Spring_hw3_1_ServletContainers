package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

// Stub
public class PostRepository {

  private final List<Post> storage;
  private long count;

  public PostRepository() {
    storage = new CopyOnWriteArrayList<>();
    count = 1;
  }

  public List<Post> all() {
    return storage;
  }

  public Optional<Post> getById(long id) {
    for (Post post : storage) {
      if (post.getId() == id) {
        return Optional.of(post);
      }
    }
    return Optional.empty();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(count);
      storage.add(post);
      count++;
      return post;
    } else {
      for (Post thisPost : storage) {
        if (thisPost.getId() == post.getId()) {
          storage.set((int)(thisPost.getId() - 1), post);
          return post;
        }
      }
    }
    return post;
  }

  public void removeById(long id) {
    for (Post post : storage) {
      if (post.getId() == id) {
        storage.remove(post);
        break;
      };
    }
  }
}
