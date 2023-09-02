# Portfolio Management API Project

## Overview

Your team is challenged with designing an application to manage a financial portfolio.

The portfolio may contain some or all of stocks, bonds, cash etc. Part of the challenge is to make use of your financial markets training to decide on what data should be stored.

The challenge has two parts:

1. Design the application on paper, with an overall system architecture diagram and user interface mockups

2. Build some of the components of the application as a Proof of Concept MINIMAL VIABLE PRODUCT. This does not need to be the complete application.

Your team may choose to emphasise part 1 or part 2 more depending on your experience and skillsets.

## Technical Goals

You should aim to create a Proof Of Concept portfolio management REST API. This will be the main target for first week of the hackathon.

This API should allow saving and retrieving records that describe the contents of a financial portofolio including all CRUD functionality.

If/When you have made progress on the core requirements then requirements for further enhancements will be provided. This will included open-ended enhancements whereby you can make use of your particular skills and experience.

We will continue working on this project into the next week of training.

IF you have user interface development experience in your team, then some of your team may begin working on a Proof of Concept user interface.


## Notes

1. There will be no authentication and a single user is assumed, i.e. there is no requirement to manage users.

2. You should use database for any persistent storage.

3. Make good use of git. Use branching and pull requests if you can.

4. Use Junit for testing your API.

## Technical Getting Started Checklist

1. Create a spring boot project.

2. Create a bitbucket repository.

3. Add, commit, push your skeleton spring-boot project to your bitbucket repository.

4. Ensure your team has access to the bitbucket repository.

5. Decide on the absolute MINIMUM fields for a first working system e.g. the first version of your model object may just be: String id, String stockTicker, int volume.

If you get stuck getting any of the above completed then contact your instructor for help.

## Project Management Getting Started Checklist

1. As a team decide how you will approach the work. E.g. 2 people on Java, 1 person on UI Design Vs. Everyone on Java until a basic system is working.

2. Make a task list. Ideally use a tool such as trello to keep track of tasks.

3. Some or your team may work on the DESIGN of a more fully-featured application. While some of your team work on BUILDING some small pieces as demonstration.

4. Choose the tasks required for a MINIMAL implementation first.

4. Your instructor will drop in regularly to see how you're progressing. Make a note of any questions so that you're ready to ask them then.

5. Your team should get together and decide on an initial set of data that you will store. A good team decision on this is a good path to success, however remember to STAY AGILE.
   The single biggest problem teams face is starting out with a data model that is too complex.

## Suggestions for Success

1. START SMALL. Get a system working that stores a very simple object with minimal fields. You can then enhance to store more complex records.

2. Try pair programming, it can be very effective.

3. Take concious steps to keep a good energy in the team. E.g. give your team a name, systematically plan check-ins with each other.

4. Emphasise quality over quantity.


## End of Hackathon

1. You will be asked to submit the URLs of your bitbucket repositories.

2. You will be asked to give your instructor a quick rundown of the status of your code.

3. You will be asked about your task list. TODO, in progress, done.

2. You may be asked to give your instructor a short group reflection. What was challenging, what steps you took to deal with those challenges etc.

##
### Appendix A: Notes on Teamwork

It is expected that you work closely as a team during this project.

Your team should be self-organising, but should raise issues with instructors if they are potential blockers to progress.

Your team should use a task management system such as Trello to keep track of tasks and progress. Divide the work

Your team should keep track of all source code with git and bitbucket.

You may choose to create a separate bitbucket repository for each component that you tackle e.g. front-end code can be in its own repository. If you create more than one spring-boot application, then each can have its own bitbucket repository. To keep track of your repositories, you can use a single bitbucket 'Project' that each of your repositories is part of.

Your instructor and team members need to access all repositories, so they should be either A) made public OR B) shared with your instructor and all team members.

Throughout your work, you should ensure good communication and organise regular check-ins with each other.

##
### Appendix B: UI Ideas

The screen below might give you some ideas about User Interfaces. You are DEFINITELY NOT expected to implement the screen below exactly as it is shown. This is JUST FOR DEMONSTRATION of the type of thing that COULD be shown.

Just to repeat.... This is NOT what is expected, it is simply here to give ideas!! In the time available, it is understood that your UI will likely be much simpler.

![Demonstration Portfolio UI](https://www.benivade.com/neueda-training/Tech2020/DemoPortfolioScreen.png)

##
### Appendix C: Useful links

Simple UI that reads live price data from yahoo finance and displays it in a web page: https://bitbucket.org/fcallaly/simple-price-ui