package ru.mai.arachnitransfer.writer;

import lombok.AllArgsConstructor;
import ru.mai.arachnitransfer.dao.ArticleDao;
import ru.mai.arachnitransfer.dao.CategoryDao;
import ru.mai.arachnitransfer.dao.CreatorDao;
import ru.mai.arachnitransfer.domain.Article;
import ru.mai.arachnitransfer.domain.Category;
import ru.mai.arachnitransfer.domain.Creator;
import ru.mai.arachnitransfer.reader.ArticleXml;
import ru.mai.arachnitransfer.service.ArticleService;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ArticleWriter {
    private final ArticleService articleService;


    void setCreatorToArticle(final Article article, final String creatorName) {
        Creator existCreator = articleService.getCreatorByName(creatorName);
        if (existCreator != null) {
            article.setCreator(existCreator);
        } else {
            Creator creator = new Creator();
            creator.setCreator(creatorName);
            article.setCreator(creator);
        }
    }

    void setCategoriesToArticle(final Article article, final List<String> categories) {
        article.setCategories(
                new HashSet<>(categories)
                        .stream()
                        .map(this::chooseCategory)
                        .collect(Collectors.toList())
        );
    }

    Category chooseCategory(final String categoryName) {
        Category existCategory
                = articleService.getCategoryByName(categoryName);
        if (existCategory != null) {
            return existCategory;
        } else {
            Category category = new Category();
            category.setCategory(categoryName);
            return category;
        }
    }
    public void write(final ArticleXml articleXml) {
        Article article = new Article();
        article.setTitle(articleXml.getTitle().text);

        setCategoriesToArticle(
                article,
                Arrays.stream(
                        articleXml
                                .getCategory()
                                .text
                                .split("/")
                        )
                        .distinct()
                        .collect(Collectors.toList())
        );
        setCreatorToArticle(article, articleXml.getCreator().text);

        article.setText(articleXml.getText().text);
        article.setCreationDate(ZonedDateTime.parse(articleXml.getCreationDate().text));

        articleService.save(article);
    }
}
