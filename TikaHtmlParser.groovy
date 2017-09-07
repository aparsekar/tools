@Grab(group = 'org.apache.tika', module = 'tika-core', version = '1.16')
@Grab(group = 'org.apache.tika', module = 'tika-parsers', version = '1.16')

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.html.HtmlParser
import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.sax.LinkContentHandler
import org.apache.tika.sax.TeeContentHandler

String someHtmlPage = 'http://www.mbtest.org/docs/examples'
InputStream input = new ByteArrayInputStream(new URL(someHtmlPage).text.getBytes("UTF-8"))
Metadata metadata = new Metadata()
BodyContentHandler bodyContentHandler = new BodyContentHandler()
LinkContentHandler links = new LinkContentHandler()

new HtmlParser().parse(input, new TeeContentHandler(links, bodyContentHandler), metadata, new ParseContext())

println([metadata: metadata, body: bodyContentHandler, links: "${links.links}"])