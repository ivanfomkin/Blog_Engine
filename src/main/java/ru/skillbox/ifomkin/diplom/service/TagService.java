package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags(String[] tagsArray);
}
