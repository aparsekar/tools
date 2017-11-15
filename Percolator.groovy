@Grapes([@Grab(group = 'org.apache.lucene', module = 'lucene-core', version = '6.0.0'),
        @Grab(group = 'org.apache.lucene', module = 'lucene-memory', version = '6.0.0'),
        @Grab(group = 'org.apache.lucene', module = 'lucene-analyzers-common', version = '6.0.0'),
        @Grab(group = 'org.apache.lucene', module = 'lucene-queryparser', version = '6.0.0')
])
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field.Store
import org.apache.lucene.document.StringField
import org.apache.lucene.document.TextField
import org.apache.lucene.index.*
import org.apache.lucene.index.IndexWriterConfig.OpenMode
import org.apache.lucene.search.*
import org.apache.lucene.store.RAMDirectory

RAMDirectory ramDirectoryIndex = new RAMDirectory()
IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer())
indexWriterConfig.setOpenMode(OpenMode.CREATE)
IndexWriter indexWriter = new IndexWriter(ramDirectoryIndex, indexWriterConfig)
indexWriter.addDocument(buildDocument())
indexWriter.close()

IndexReader reader = DirectoryReader.open(ramDirectoryIndex)
IndexSearcher indexSearcher = new IndexSearcher(reader)
List matchingQueries = [new TermQuery(new Term('author', 'Javier Perrara')),
                        new TermQuery(new Term('contents', 'printing')),
                        new TermQuery(new Term('chapters', 'Chapter 7: The Rebellion of Ostia.')),
                        new TermQuery(new Term('chapters', 'Chapter 7: Death to Smoochie.')),
                        new MatchAllDocsQuery(),
                        new PhraseQuery('author', 'Letty Raines')]
        .findAll { (indexSearcher.search(it, 1, new Sort()).totalHits == 1) }

println('Matching queries - ')
matchingQueries.each { println("[${it.class.simpleName} - ${it}]") }

Document buildDocument() {
    /**
     * Use StringField in order to perform an exact match
     */
    Document doc = new Document()
    doc.add(new StringField('title', 'Good Behavior', Store.YES))
    doc.add(new StringField('author', 'Javier Perrara', Store.YES))
    doc.add(new StringField('publisher', 'Scholastic', Store.YES))
    ['Chapter 1: Dawn of Destiny.',
     'Chapter 2: The Princess of Bern.',
     'Chapter 3: Late Arrival.',
     'Chapter 4: Collapse of the Alliance.',
     'Chapter 5: The Emblem of Fire.',
     'Chapter 6: The Trap.',
     'Chapter 7: The Rebellion of Ostia.',
     'Chapter 8: The Reunion.'].each { doc.add(new StringField('chapters', it, Store.YES)) }
    doc.add(new TextField('contents', '' +
            'Lorem Ipsum is simply dummy text of the printing and typesetting industry. ' +
            'Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, ' +
            'when an unknown printer took a galley of type and scrambled it to make a type ' +
            'specimen book. It has survived not only five centuries, but also the leap into ' +
            'electronic typesetting, remaining essentially unchanged. It was popularised ' +
            'in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, ' +
            'and more recently with desktop publishing software like Aldus PageMaker including ' +
            'versions of Lorem Ipsum.', Store.YES))
    doc
}