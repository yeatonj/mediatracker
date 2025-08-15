package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.Tag;
import me.yeaton.mediatracker.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    // Create
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // Read
    public Iterable<Tag> fetchTags() {
        return tagRepository.findAll();
    }

    // Update
    public Tag updateTag(Tag tag, UUID id) {
        Tag tagDB = tagRepository.findById(id).get();
        if (Objects.nonNull(tag.getTag()) && !"".equalsIgnoreCase(tag.getTag())) {
            tagDB.setTag(tag.getTag());
        }
        return tagRepository.save(tagDB);
    }

    // Delete
    public void deleteTag(UUID id) {
        tagRepository.deleteById(id);
    }
}
