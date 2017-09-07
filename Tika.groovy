@Grab(group = 'org.apache.tika', module = 'tika-core', version = '1.16')
@Grab(group = 'org.apache.tika', module = 'tika-parsers', version = '1.16')

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.html.HtmlParser
import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.sax.LinkContentHandler
import org.apache.tika.sax.TeeContentHandler

String html = '''<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>JSONPlaceholder - Fake online REST API for developers</title>
<meta name="description" content="Fake online REST API for developers">
<meta name="author" content="Typicode">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/styles/github.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<link rel="stylesheet" type="text/css" href="style.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>
<header>
<h1><img src="http://i.imgur.com/QRlAg0b.png"><br>JSONPlaceholder </h1>
<p>
Fake Online REST API for Testing and Prototyping<br>
<small>
powered by <a href="https://github.com/typicode/json-server">JSON Server</a>
and <a href="https://github.com/typicode/lowdb">lowdb</a>
</small>
</p>
</header>
<div class="narrow">
<div class="center">

<div class="inline">
<div id="fb-root"></div>
<script>(function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
            js = d.createElement(s); js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
            fjs.parentNode.insertBefore(js, fjs);
          }(document, 'script', 'facebook-jssdk'));</script>
<div class="fb-like" data-href="http://jsonplaceholder.typicode.com/" data-layout="button_count" data-action="recommend" data-show-faces="false" data-share="false"></div>
</div>
<div class="inline">
<a class="twitter-follow-button" href="https://twitter.com/typicode" data-show-count="false">
Follow @typicode
</a>
</div>

</div>
<h2>Intro</h2>
<p>
JSONPlaceholder is a free online REST service that you can use whenever you need some fake data.
</p>
<p>
It's great for tutorials, faking a server, sharing code examples, ...
</p>
<h2>Example</h2>
<p>Run this code in a console or from anywhere (CORS and JSONP supported).</p>
<pre><code id="example" class="javascript">var root = 'http://jsonplaceholder.typicode.com';

$.ajax({
  url: root + '/posts/1',
  method: 'GET\'
}).then(function(data) {
  console.log(data);
});</code></pre>
<p>
<button id="run">Run</button>
</p>
<p id="result" class="json">"Try me!"</p>
<h2>Resources</h2>
<p>
Inspired by common use cases.
</p>
<table>
<tr><td><a href="/posts">/posts</a></td><td>100 items</td></tr>
<tr><td><a href="/comments">/comments</a></td><td>500 items</td></tr>
<tr><td><a href="/albums">/albums</a></td><td>100 items</td></tr>
<tr><td><a href="/photos">/photos</a></td><td>5000 items</td></tr>
<tr><td><a href="/todos">/todos</a></td><td>200 items</td></tr>
<tr><td><a href="/users">/users</a></td><td>10 items</td></tr>
</table>
<h2>Routes</h2>
<p>
All HTTP verbs are supported.<br>
View usage <a href="https://github.com/typicode/jsonplaceholder#how-to">examples</a>.
</p>
<table>
<tr><td class="verb">GET</td><td><a href="/posts">/posts</a></td></tr>
<tr><td class="verb">GET</td><td><a href="/posts/1">/posts/1</a></td></tr>
<tr><td class="verb">GET</td><td><a href="/posts/1/comments">/posts/1/comments</a></td></tr>
<tr><td class="verb">GET</td><td><a href="/comments?postId=1">/comments?postId=1</a></td></tr>
<tr><td class="verb">GET</td><td><a href="/posts?userId=1">/posts?userId=1</a></td></tr>
<tr><td class="verb">POST</td><td>/posts</td></tr>
<tr><td class="verb">PUT</td><td>/posts/1</td></tr>
<tr><td class="verb">PATCH</td><td>/posts/1</td></tr>
<tr><td class="verb">DELETE</td><td>/posts/1</td></tr>
</tr>
</table>
<h2>Use your OWN data</h2>
<p>
<a href="https://github.com/typicode/json-server">JSON Server</a> powers this website.
You can use it to create the same fake API in less than <strong>30 seconds</strong> with your own data.
</p>
<pre><code class="bash">npm install -g json-server</code></pre>
<p>Or you can try <a href="https://my-json-server.typicode.com">My JSON Server</a> free service.</p>
</div>
<footer>
<p>
Coded and built with <i class="fa fa-heart"></i>
by <a href="https://github.com/typicode">typicode</a>.
Source code available on
<a href="https://github.com/typicode/jsonplaceholder">GitHub</a>.
<a href="https://patreon.com/typicode" onclick="trackOutboundLink('https://patreon.com/typicode')">Patreon page</a>.
</p>
</footer>
<a href="https://github.com/typicode/hotel" class="more">
<p>
Hotel - 🏩 the friendly process manager for developers
</p>
</a>


<script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

      ga('create', 'UA-44497010-1', 'typicode.com');
      ga('send', 'pageview');

      var trackOutboundLink = function(url) {
        ga('send', 'event', 'outbound', 'click', url, {
          'transport': 'beacon\'
        });
      }
    </script>
<script>window.twttr = (function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0],
        t = window.twttr || {};
      if (d.getElementById(id)) return t;
      js = d.createElement(s);
      js.id = id;
      js.src = "https://platform.twitter.com/widgets.js";
      fjs.parentNode.insertBefore(js, fjs);

      t._e = [];
      t.ready = function(f) {
        t._e.push(f);
      };

      return t;
    }(document, "script", "twitter-wjs"));</script>
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
      // Use http or https based on location.protocol
      var exampleText = $('#example').text()
      $('#example').text(exampleText.replace('http:', location.protocol))

      // Highlight result element
      $('#result').each(function(i, block) {
        hljs.highlightBlock(block);
      });

      // Run example
      $('#run').click(function() {
        var root = location.protocol + '//jsonplaceholder.typicode.com';
        $.ajax({
          url: root + '/posts/1',
          method: 'GET\'
        }).then(function(data) {
          var str = JSON.stringify(data, null, '\\t')
          $('#result').html(
            str.replace(/\\n/g, '<br/>')
               .replace(/\\\\n/g, ' ')
               .replace(/\\t/g, '&nbsp;&nbsp;')
          );

          $('#result').each(function(i, block) {
            hljs.highlightBlock(block);
          });
        });
      });

      // Tell that jQuery can be used in console
      console.log('You can use jQuery functions')
    </script>
</body>
</html>
'''
InputStream input = new ByteArrayInputStream(html.getBytes("UTF-8"))
LinkContentHandler links = new LinkContentHandler()
Metadata metadata = new Metadata()
ParseContext context = new ParseContext()
new HtmlParser().parse(input, new TeeContentHandler(links, new BodyContentHandler()), metadata, context)
System.out.println("Title: " + metadata.get(Metadata.TITLE))
links.links.each { println(it) }