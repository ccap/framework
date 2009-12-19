package net.liftweb.mocks

import _root_.scala.collection.mutable.HashMap
import _root_.java.io.PrintWriter
import _root_.java.io.StringReader
import _root_.java.io.BufferedReader
import _root_.java.io.ByteArrayOutputStream
import _root_.java.io.ByteArrayInputStream
import _root_.java.io.FileInputStream
import _root_.java.io.InputStream
import _root_.java.io.StringBufferInputStream
import _root_.java.io.File
import _root_.java.util.Arrays
import _root_.java.util.Date
import _root_.java.util.Locale
import _root_.java.util.Vector
import _root_.javax.servlet._
import _root_.javax.servlet.http._

/**
 * A Mock HttpServletResponse. Take a peek at it's writer or
 * outputStream to see what lift has written in response to your request
 *
 * @param writer a PrintWriter that the response will be written with
 * @param outputStream an OutputStream that the response will be written to.
 *
 * @author Steve Jenson (stevej@pobox.com)
 */
class MockHttpServletResponse(var writer: PrintWriter, var outputStream: ServletOutputStream)
  extends HttpServletResponse {
  var statusCode : Int = 200
  var statusString : String = "OK"
  var contentType = "text/html"
  var contentLength = 0
  val headers: HashMap[String, String] = new HashMap[String, String]
  var cookies: List[Cookie] = Nil
  var locale: Locale = Locale.getDefault
  var bufferSize: Int = 0
  var charEncoding = "ISO-8859-1" // yes, that's HTTP's default

  def setStatus(i: Int, s: String) = {
    statusCode = i
    statusString = s
  }

  def setStatus(i: Int) = {
    statusCode = i
  }

  def addIntHeader(s: String, i: Int) {
    headers += (s -> i.toString)
  }
  def setIntHeader(s: String, i: Int) {
    headers += (s -> i.toString)
  }
  def addHeader(s1: String, s2: String) {
    headers += (s1 -> s2)
  }
  def setHeader(s1: String, s2: String) {
    headers += (s1 -> s2)
  }

  def addDateHeader(s: String, l: Long) {
    headers += (s -> (new Date(l)).toString)
  }
  def setDateHeader(s: String, l: Long) {
    addDateHeader(s, l)
  }

  def sendRedirect(uri: String) {
    // Send back a 301 to the URL mentioned
    statusCode = 301
    addHeader("Location", uri)
  }

  def sendError(code: Int) {
    statusCode = code
  }

  def sendError(code: Int, s: String) {
    sendError(code)
    statusString = s
  }

  def encodeRedirectURL(url: String): String = encodeRedirectUrl(url)
  def encodeRedirectUrl(url: String): String = {
    // do something fancy encoding on uri, return that.
    url
  }
  def encodeURL(url: String): String = encodeUrl(url)
  def encodeUrl(url: String): String = {
    // use the same encoder as encodeRedirectUrl
    url
  }
  def containsHeader(header: String): Boolean = {
    headers.contains(header)
  }
  def addCookie(cookie: Cookie) = {
    cookies =  cookie :: cookies
  }
  def getLocale: Locale = locale
  def setLocale(l: Locale) = locale = l
  def reset {
    // well, reset all the state to it's original values. yikes. later.
  }
  def isCommitted = false
  def resetBuffer {
    // reset the buffer.
  }
  def flushBuffer {
    // flush the buffer
  }
  def getBufferSize = bufferSize
  def setBufferSize(i: Int) = bufferSize = i
  def setContentType(t: String) = contentType = t
  def setContentLength(l: Int) = contentLength = l
  def setCharacterEncoding(e: String) = charEncoding = e
  def getWriter = writer
  def getOutputStream = outputStream
  def getContentType = contentType
  def getCharacterEncoding = charEncoding
}
