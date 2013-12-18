package pages

import geb.Page

/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 18.12.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
class WikipediaPage extends Page {
    static at = { title == "Wikipedia, the free encyclopedia" }
}