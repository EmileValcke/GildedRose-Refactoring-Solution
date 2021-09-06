package main.java.com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (isAgedBrie(item)) {
            updateAgedBrie(item);
        }
        else if(isBackstagePasses(item)) {
            updateBackstagePasses(item);
        }
        else if(isConjured(item)) {
            updateConjured(item);
        }
        else if(isLegendary(item)) {
        }
        else {
            updateNormal(item);
        }
    }

    private void updateNormal(Item item) {
        if(isConcert(item)) {
            increaseQuality(item, -2);
            }
        else {
            increaseQuality(item, -1);
        }
        decreaseSellIn(item);
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item, 1);
        decreaseSellIn(item);
    }

    private void updateBackstagePasses(Item item) {
        if(item.sellIn <= 0) {
            item.quality = 0;
        }
        else if(item.quality < 50) {
            Integer multiplier = qualityMultiplier(item.sellIn);
            increaseQuality(item, multiplier);
        }
        decreaseSellIn(item);
    }

    private void updateConjured(Item item) {
        if(isConcert(item)) {
            increaseQuality(item, -4);
        }
        else {
            increaseQuality(item, -2);
        }
        decreaseSellIn(item);
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private Integer qualityMultiplier(Integer remainingSellIn) {
        if(remainingSellIn < 6) {
            return 3;
        }
        else if (remainingSellIn < 11) {
            return 2;
        }
        else {
            return 1;
        }
    }

    private void increaseQuality(Item item, Integer i) {
        item.quality = item.quality + i;
        checkThreshold(item);
    }

    private void checkThreshold(Item item) {
        if(item.quality > 50) {
            item.quality = 50;
        }
        else if(item.quality < 0) {
            item.quality = 0;
        }
    }

    private boolean isConcert(Item item) {
        return item.sellIn <= 0;
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private static boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isConjured(Item item) {
        return item.name.equals("Conjured");
    }
}
