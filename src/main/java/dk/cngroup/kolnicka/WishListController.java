package dk.cngroup.kolnicka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.cngroup.kolnicka.repository.WishRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WishListController {
    private final WishRepository wishRepository;

    @GetMapping("/new-wish")
    public Wish getNewWish() {
       return wishRepository.save(Wish.builder()
                .description("White t-shirt")
                .priority(3)
                .build());
    }

    @GetMapping("/wishList")
    public ResponseEntity<Iterable<Wish>> getWishList() {
        return ResponseEntity.ok(wishRepository.findAll());
    }
}
