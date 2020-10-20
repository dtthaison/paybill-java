# Setup:
1. install mvn: `brew install mvn`
2. run `mvn clean & mvn package`
3. run `java -jar target/interview-1.0.0-SNAPSHOT.jar <command> <args...>`

### Valid commands:
    1. CLEAR: clear all db file
    2. TOPUP <amount>: add amount to account
    3. BILL_ALL: get all bill
    4. BILL_ADD <type> <amount>: add bill
    5. BILL_PAY <billIds:billId1,billId2,...>: pay bill with ids