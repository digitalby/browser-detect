package me.digitalby.browserdetect

import me.digitalby.browserdetect.internal.BrowserCatalog
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BrowserCatalogTest {
    @Test
    fun `every catalog entry has a unique BrowserId`() {
        val ids = BrowserCatalog.entries.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun `every primary package is unique`() {
        val pkgs = BrowserCatalog.entries.map { it.primaryPackage }
        assertEquals(pkgs.size, pkgs.toSet().size)
    }

    @Test
    fun `byPackage map contains every primary and alternate`() {
        BrowserCatalog.entries.forEach { entry ->
            assertTrue("missing ${entry.primaryPackage}", BrowserCatalog.byPackage.containsKey(entry.primaryPackage))
            entry.alternatePackages.forEach { alt ->
                assertTrue("missing alternate $alt", BrowserCatalog.byPackage.containsKey(alt))
            }
        }
    }

    @Test
    fun `no entry uses BrowserId Unknown`() {
        BrowserCatalog.entries.forEach { entry ->
            assertFalse(entry.id is BrowserId.Unknown)
        }
    }
}
