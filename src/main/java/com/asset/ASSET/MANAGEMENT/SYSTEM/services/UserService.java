package com.asset.ASSET.MANAGEMENT.SYSTEM.services;
import com.asset.ASSET.MANAGEMENT.SYSTEM.Repository.UserRepository;
import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.Role;
import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.User;
import com.asset.ASSET.MANAGEMENT.SYSTEM.exception.ForbiddenException;
import com.asset.ASSET.MANAGEMENT.SYSTEM.exception.InvalidStateException;
import com.asset.ASSET.MANAGEMENT.SYSTEM.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {

    private final UserRepository u;

    public UserService(UserRepository u) {
        this.u = u;
    }

    public User create(User user) {
        if (u.existsByEmail(user.getEmail())) {
            throw new InvalidStateException("Email already in use: " + user.getEmail());
        }
        return u.save(user);
    }

    public List<User> getAll() {
        return u.findAll();
    }

    public User getById(Long id) {
        return u.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found " + id));
    }

    public User update(Long id, User change) {
        User existing = getById(id);
        existing.setName(change.getName());
        existing.setEmail(change.getEmail());
        existing.setRole(change.getRole());
        return u.save(existing);
    }

    public void delete(Long id) {
        u.delete(getById(id));
    }


    public void requireAdmin(Long dd) {
        User p = getById(dd);
        if (p.getRole() != Role.ADMIN) {
            throw new ForbiddenException("admin can perform this action");
        }
    }
}