# Overview
This project can serve as a client for the Postgresql LctMsg API.

# Links

# SQL Statements

```
select * from lct_msg_y where vc_date='2022-05-09';
select * from lct_msg_y where vc_date='2022-05-10' AND vc_trip='4457006';
select COUNT(*) from lct_msg_y where vc_date='2022-05-10' AND vc_trip='4457006';
SELECT
      CASE WHEN EXISTS 
      (
            SELECT * from lct_msg_y where vc_date='2022-05-10' AND vc_trip='4457006'
      )
      THEN 'TRUE'
      ELSE 'FALSE'
END;
```
