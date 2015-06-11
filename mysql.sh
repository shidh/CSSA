#!/bin/bash  
# Define log  
TIMESTAMP=`date +%Y%m%d%H%M%S`  
LOG=call_sql_${TIMESTAMP}.log  
echo "Start execute sql statement at `date`." >>${LOG}  
  
# execute sql stat  
mysql -uallen -pMunich2014!  -e '
tee /tmp/temp.log  
use cssa;
select * from user_table where major="maschinenbau"
'
