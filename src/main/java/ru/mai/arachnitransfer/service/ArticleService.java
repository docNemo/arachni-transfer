package ru.mai.arachnitransfer.service;

import lombok.RequiredArgsConstructor;
import ru.mai.arachnitransfer.dao.ArticleDao;
import ru.mai.arachnitransfer.dao.CategoryDao;
import ru.mai.arachnitransfer.dao.CreatorDao;
import ru.mai.arachnitransfer.domain.Article;
import ru.mai.arachnitransfer.domain.Category;
import ru.mai.arachnitransfer.domain.Creator;

@RequiredArgsConstructor
public class ArticleService {
    private final ArticleDao articleDao;
    private final CreatorDao creatorDao;
    private final CategoryDao categoryDao;

    public void save(Article article) {
        articleDao.save(article);
    }

    public void save(Creator creator) {
        creatorDao.save(creator);
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public Creator getCreatorByName(String name) {
        return creatorDao.getByName(name);
    }

    public Category getCategoryByName(String name) {
        return categoryDao.getByName(name);
    }

}
