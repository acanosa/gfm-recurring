# gfm-recurring
The application takes a list of commands and generates a summary of all the monthly donations, indicanting donor totals and averages and the total recaudation of campaigns in that month.

## Getting started
You will need these tools to build and run the application:
* Java 17
* Maven

## Commands
* Add Donor [Name] $[MonthlyLimit]: Adds a donor and sets his/her monthly limit for donations
* Add Campaign [Name]: Adds a campaign
* Donate [DonorName] [CampaignName] $[amount]: Creates a donation of the donor to the campaign. Note: if the monthly limit of the donor is surpassed by the donation, it will be ignored.

## Building and testing the application

1. Execute the command `mvn package` to generate the JAR file. It will be created inside the `target` folder
2. Copy that JAR to the location of your choice
3. Take the file to that same location and execute the command `java -jar gfm-recurring.jar YOURTEXTFILE.txt`. You can also use the cat command for input: `cat YOURFILE.txt | java -jar gfm-recurring.jar`

## Considerations

* Commands are case sensitive. For example, if you use `Add donor` instead of `Add Donor`, it will do nothing.
* Commands must be complete. Please refer to the Commands section
* Input file must be a text file (.txt)
