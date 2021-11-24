package dk.cngroup.kolnicka.wishlist;

import org.springframework.data.repository.CrudRepository;

public interface WishRepository extends CrudRepository<Wish, Long> {
    Wish findTopByOrderByIdDesc();
}
