-- business tree
SELECT
  base_h.id AS hierarchy_id,
  base_h.parent as parent_id,
  base_h.type AS hierarchy_type,
  IFNULL(a.agent_name, p.code) AS name,
  IFNULL(p.code, pp.code) AS partner_code,
  base_h.financial_account_id AS financial_account_id,
  base_h.lft AS lft,
  base_h.rgt AS rgt,
  IF((IFNULL(p.is_enabled, a.is_enabled) = 1), 1, 0) AS is_enabled
FROM
  business_hierarchy bh
  JOIN business_hierarchy base_h ON bh.lft <= base_h.lft AND bh.rgt >= base_h.rgt
  LEFT JOIN partners p ON p.hierarchy_id = base_h.id
  LEFT JOIN agents a ON a.hierarchy_id = base_h.id
  LEFT JOIN partners pp ON pp.hierarchy_id = a.label_id
WHERE
  bh.id = 1
ORDER BY bh.lft , base_h.lft;

-- hierarchy structure
SELECT
    CONCAT(REPEAT(' ', COUNT(parent.id) - 1),
            IFNULL(a.agent_name, p.code)) AS name,
    p.code AS partner_code,
    node.id AS hierarchy_id,
    node.parent,
    node.type,
    node.lft,
    node.rgt
FROM
    business_hierarchy AS node
        LEFT JOIN
    partners AS p ON node.id = p.hierarchy_id
        LEFT JOIN
    agents AS a ON node.id = a.hierarchy_id,
    business_hierarchy AS parent
WHERE
    node.lft BETWEEN parent.lft AND parent.rgt
GROUP BY node.id
ORDER BY node.lft;

-- player count
SELECT bh.id AS hierarchy_id,
       count(u.id) AS total_player_count
FROM business_hierarchy bh
  JOIN business_hierarchy base_h ON bh.lft <= base_h.lft AND bh.rgt >= base_h.rgt
  LEFT JOIN users u ON u.hierarchy_id = base_h.id
GROUP BY bh.id
ORDER BY bh.lft ASC;

-- player count & player balance
SELECT bh.id AS hierarchy_id,
       sum(fa.balance) as players_total_balance,
       count(u.id) AS total_player_count
FROM business_hierarchy bh
  JOIN business_hierarchy base_h ON bh.lft <= base_h.lft AND bh.rgt >= base_h.rgt
  JOIN users u ON u.hierarchy_id = base_h.id
  JOIN financial_accounts fa on u.financial_account_id = fa.id
GROUP BY bh.id
ORDER BY bh.lft ASC;

-- agent balance
SELECT
    base_h.id AS hierarchy_id,
    base_h.type AS hierarchy_type,
    IFNULL(a.agent_name, p.code) AS name,
    IFNULL(p.code, pp.code) AS partner_code,
    base_h.financial_account_id AS financial_account_id,
    base_h.lft AS lft,
    base_h.rgt AS rgt,
    IF((IFNULL(p.is_enabled, a.is_enabled) = 1),
        1,
        0) AS is_enabled,
    IFNULL(fa.balance, 0) AS agent_balance,
    SUM(IFNULL(fa_downline.balance, 0)) AS total_agent_balance
FROM
    business_hierarchy bh
        JOIN
    business_hierarchy base_h ON bh.lft <= base_h.lft
        AND bh.rgt >= base_h.rgt
        LEFT JOIN
    partners p ON p.hierarchy_id = base_h.id
        LEFT JOIN
    agents a ON a.hierarchy_id = base_h.id
        LEFT JOIN
    partners pp ON pp.hierarchy_id = a.label_id
        JOIN
    financial_accounts AS fa_downline ON base_h.financial_account_id = fa_downline.id
        JOIN
    financial_accounts AS fa ON bh.financial_account_id = fa.id
GROUP BY bh.id
ORDER BY bh.lft , base_h.lft;


SELECT
  *
FROM
  users;