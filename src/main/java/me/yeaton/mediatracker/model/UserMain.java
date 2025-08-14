package me.yeaton.mediatracker.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class UserMain {
    
    @Id
    private UUID id;
    private String username;
    private String email;
    private String role;
    private String password;
    private LocalDateTime birthday;
    private LocalDateTime createdAt;
    // user's wishlist
    private Set<BookWishlist> wishlistBooks = new HashSet<>();
    // user's books read
    private Set<BookRead> booksRead = new HashSet<>();
    
    public UserMain(String username, String email, String role, String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public LocalDateTime getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<BookWishlist> getWishlistBooks() {
        return wishlistBooks;
    }

    public void setWishlistBooks(Set<BookWishlist> wishlistBooks) {
        this.wishlistBooks = wishlistBooks;
    }

    public void addWishListBook(BookWishlist bookWishlist) {
        this.wishlistBooks.add(bookWishlist);
        bookWishlist.userMain = this;
    }

    public Set<BookRead> getBooksRead() {
        return booksRead;
    }

    public void setBooksRead(Set<BookRead> booksRead) {
        this.booksRead = booksRead;
    }

    public void addBookRead(BookRead bookRead) {
        this.booksRead.add(bookRead);
        bookRead.userMain = this;
    }

    @Override
    public String toString() {
        return "UserMain [id=" + id + ", username=" + username + ", email=" + email + ", role=" + role + ", password="
                + password + ", birthday=" + birthday + ", createdAt=" + createdAt + ", wishlistBooks=" + wishlistBooks
                + ", booksRead=" + booksRead + "]";
    }
}

