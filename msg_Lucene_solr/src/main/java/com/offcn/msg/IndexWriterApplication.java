package com.offcn.msg;


import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;


import java.io.File;

/**
 * Created by Administrator on 2019/10/12.
 */
public class IndexWriterApplication {


    @Test
    public void indextest() throws Exception {
        //1. 创建一个索引写入器对象

        File file = new File("E:/indextemp/temp");
        Directory directory = FSDirectory.open(file);

        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);


        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //2. 添加原始文档: 文档 看做是一个文件或者数据库中的一个表

        File[] files = new File("E:\\indextemp\\resources").listFiles();
        for (File file1 : files) {
            Document document = new Document();
            //文件名称
            String file_name = file1.getName();
            Field fileNameFiled = new TextField("filename", file_name, Field.Store.YES);

            //文件路径
            String file_path = file1.getAbsolutePath();
            Field filePathFiled = new StoredField("filepath", file_path);

            //文件内容
            String file_content = FileUtils.readFileToString(file1);
            Field fileContentFiled = new TextField("filecontent", file_content, Field.Store.YES);

            document.add(fileNameFiled);
            document.add(filePathFiled);
            document.add(fileContentFiled);

            indexWriter.addDocument(document);
        }

        //3. 提交数据

        indexWriter.commit();

        //4. 关闭索引写入器

        indexWriter.close();
    }


    public void publicSearch(Query query) throws Exception {

        //创建一个Directory对象  索引库的存放位置
        FSDirectory directory = FSDirectory.open(new File("E:/indextemp/temp"));

        //创建indexReader 对象  需要制定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);

        //创建indexSearcher对象 需要制定indexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //创建一个TermQuery对象 需要制定查询的域和查询的关键词
       // Query query = new TermQuery(new Term("filecontent", "public"));

        //执行查询
        TopDocs search = indexSearcher.search(query,Integer.MAX_VALUE);

        //返回查询结果  遍历结果并且输出
        ScoreDoc[] scoreDocs = search.scoreDocs;

        System.out.println(scoreDocs.length);
        for (ScoreDoc scoreDoc : scoreDocs) {
            //得到document的id
            int doc = scoreDoc.doc;

            //得到document对象
            Document doc1 = indexSearcher.doc(doc);

            //输出文件名
            System.out.println(doc1.get("filename"));
        }

    }


    @Test
    /**
     // 1.多样化查询: 词条查询
     // 词条查询是一个不可在分割内容, 可以把词条看做是一个分词后的单词
     //词条查询在书写词条内容的时候, 是不允许输入错误的
     // 可以使用词条查询: 查询 不需要在进行分词的字段: id(StringFeild)
    */
    public void termQeryTest() throws Exception {
        //1. 创建termQuery对象
        TermQuery query = new TermQuery(new Term("filecontent","java"));
        publicSearch(query);
    }


    //2. 通配符查询: WildcardQuery : 类似SQL中的like查询: _ 和 %
    // * : 占用 0 ~ 多个字符
    // ? : 占用一个字符
    @Test
    public void wildcardQueryTest() throws Exception {

        WildcardQuery query = new WildcardQuery(new Term("filename","*a*"));

        publicSearch(query);
    }

    @Test
    public void queryParseTest() throws Exception {

        QueryParser queryParser = new QueryParser("filename", new IKAnalyzer());
        Query parse = queryParser.parse("j*");

        publicSearch(parse);
    }


}
