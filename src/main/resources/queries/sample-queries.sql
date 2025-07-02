SELECT
    feed_date,
    SUM(feed_count) AS total_count,
    CASE
        WHEN SUM(feed_count) BETWEEN 0 AND 20 THEN 'Category 1'
        WHEN SUM(feed_count) > 20 AND SUM(feed_count) <= 40 THEN 'Category 2'
        WHEN SUM(feed_count) > 40 THEN 'Category 3'
        ELSE 'Uncategorized'
        END AS category
FROM
    feed_count
GROUP BY
    feed_date
ORDER BY
    feed_date;
