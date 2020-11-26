
-- ============= available ============
ALTER TABLE "Calendar"
ALTER COLUMN available TYPE boolean
USING available::boolean;

-- ============= price ============
UPDATE "Calendar"
SET price = REPLACE(price,'$','')
WHERE price IS NOT NULL;

UPDATE "Calendar"
SET price = REPLACE(price,',','')
WHERE price IS NOT NULL;

ALTER TABLE "Calendar" 
ALTER COLUMN price TYPE numeric(10,0) 
USING price::numeric;

-- ========== adjusted_price ===========

UPDATE "Calendar"
SET adjusted_price = REPLACE(adjusted_price,'$','')
WHERE adjusted_price IS NOT NULL;

UPDATE "Calendar"
SET adjusted_price = REPLACE(adjusted_price,',','')
WHERE adjusted_price IS NOT NULL;

ALTER TABLE "Calendar"
ALTER COLUMN adjusted_price TYPE numeric(10,0)
USING price::numeric;

