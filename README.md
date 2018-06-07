# Group12
Project repo for Group 12

## Members
Zongming Liu (B00784897) - zongming.liu@dal.ca

David Cui (B00788648) - yq506499@dal.ca

Zaher Abd Ulmoula (B00761188) - mh979648@dal.ca

Youran Zhang (B00779932) - yr994423@dal.ca

Rahul Vala (B00785077) - rh318779@dal.ca

## About Project
Our project is building a full-stack website for finding tutors in Nova Scotia. People can register as student or tutor. There is also an admin access which allows the banning of other users. People not logged in can use our search bar to find list of tutors registered on our website (search result), but in order to view the tutor's full profile and book appointments, the user must log in. Please see our project document for greater detail of the project.

## Deployment
To deploy to devint - `mvn azure-webapp:deploy`

To deploy to test - `mvn -f test-pom.xml azure-webapp:deploy`

To deploy to prod - `mvn -f prod-pom.xml azure-webapp:deploy`

Testing Milestones
