package main.java.com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQualityTest() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        //sellin should be decreased by one
        assertEquals("foo, -1, 0", app.items[0].toString());
    }

    @Test
    void lowerQuality_SellIn_NormalTest() throws Exception {
        Item[] items = new Item[] { new Item("Normal Item", 5, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void lowerQuality_Twice_NormalTest() throws Exception {
        Item[] items = new Item[] { new Item("Normal Item", 0, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void negativeQuality_Test() throws Exception {
        Item[] items = new Item[] {new Item("Normal Item", 5, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void increaseQuality_AgedBrieTest() throws Exception {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 5)  };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void qualityAbove_50_AgedBrieTest() throws Exception {
        Item[] items = new Item[] {new Item("Aged Brie", 5, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void increaseQuality_Normal_BackstageTest() throws Exception {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 20, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void increaseQuality_Twice_BackstageTest() throws Exception {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(7, app.items[0].quality);
    }

    @Test
    void increaseQuality_ThreeTimes_BackstageTest() throws Exception {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void qualityAbove_50_BackstageTest() throws Exception {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void degradeQuality_Zero_BackstageTest() throws Exception {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void degradeQuality_Twice_ConjuredTest() throws Exception {
        Item[] items = new Item[] {new Item("Normal Item", 5, 5), new Item("Conjured", 5, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(4, app.items[1].sellIn);
        assertEquals(4, app.items[0].quality);
        assertEquals(3, app.items[1].quality);
    }

    @Test
    void nothingHappens_80Quality_LegendaryTest() throws Exception {
        Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 5, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

}
