package ru.mai.arachnitransfer;
import ru.mai.arachnitransfer.dao.ArticleDao;
import ru.mai.arachnitransfer.dao.CategoryDao;
import ru.mai.arachnitransfer.dao.CreatorDao;
import ru.mai.arachnitransfer.reader.ArticleReader;
import ru.mai.arachnitransfer.service.ArticleService;
import ru.mai.arachnitransfer.transfer.Transfer;
import ru.mai.arachnitransfer.writer.ArticleWriter;

import java.util.concurrent.SynchronousQueue;

public class TransferApplication {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();
        CreatorDao creatorDao = new CreatorDao();
        CategoryDao categoryDao = new CategoryDao();
        ArticleService articleService = new ArticleService(articleDao, creatorDao, categoryDao);
        ArticleWriter articleWriter = new ArticleWriter(articleService);
        ArticleReader articleReader = new ArticleReader();
        Transfer transfer = new Transfer(articleReader, articleWriter, new SynchronousQueue<>());

        transfer.start();
    }

}
