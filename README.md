# gfm-recurring
The application takes a list of commands and generates a summary of all the monthly donations, indicanting donor totals and averages and the total recaudation of campaigns in that month.
Author: Alejandro Canosa

Here is a sample output of a summary of monthly donations having 3 donors and 2 campaigns:
```
Donors:
Alex: Total: $150 Average: $75
Benjamin: Total: $60 Average: $60
Greg: Total: $100 Average: $100

Campaigns:
HelpTheKids: Total: $50
SaveTheDogs: Total: $260
```

## Getting started
You will need these tools to build and run the application:
* Java 17
* Maven

## Commands
* Add Donor [Name] $[MonthlyLimit]: Adds a donor and sets his/her monthly limit for donations
* Add Campaign [Name]: Adds a campaign
* Donate [DonorName] [CampaignName] $[amount]: Creates a donation of the donor to the campaign. Note: if the monthly limit of the donor is surpassed by the donation, it will be ignored.

## Considerations

* Commands are case sensitive. For example, if you use `Add donor` instead of `Add Donor`, it will do nothing.
* Commands must be complete. Please refer to the Commands section
* Input file must be a text file (.txt)

## Building and testing the application

1. Execute the command `mvn package` to generate the JAR file. It will be created inside the `target` folder
2. Copy that JAR to the location of your choice
3. Take the file to that same location and execute the command `java -jar gfm-recurring.jar YOURTEXTFILE.txt`. You can also use the cat command for input: `cat YOURFILE.txt | java -jar gfm-recurring.jar`

You can execute unit tests by running the command `mvn test`

Here is a sample content for a test file:
```
Add Donor Greg $1000
Add Donor Alex $2000
Add Donor Benjamin $3000
Add Campaign SaveTheDogs
Add Campaign HelpTheKids
Donate Greg SaveTheDogs $100
Donate Alex HelpTheKids $50
Donate Alex SaveTheDogs $100
Donate Benjamin SaveTheDogs $6000
Donate Benjamin SaveTheDogs $60
```

## Troubleshooting
 
### Error: Unable to access jarfile

Check that the jar name is correct or if it is in the folder that you are executing the command

### Invalid target release when building with Maven

Make sure to be using Java 17 on the command prompt


