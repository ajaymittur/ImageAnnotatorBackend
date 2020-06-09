# ImageAnnotatorBackend

A REST API with a micro-service architecture for an [image labelling portal](https://hardcore-euler-0846b3.netlify.app/). 
Code for the frontend that consumes this REST API is present [here](https://github.com/akshaymittur/ImageAnnotatorFrontend).

## Tech Stack

- Front-End: [**React**](https://reactjs.org/)
- Back-End: [**Spring Boot**](https://spring.io/projects/spring-boot)
- Database: [**MySQL (AWS RDS)**](https://www.mysql.com/)
- Storage: [**AWS S3**](https://aws.amazon.com/s3/)

## Routes

- `/admin`
  - Handle admin registration, login and other admin controls like private messaging to annotator
- `/annotator`
  - Handle annotator registration, login, messaging with admin and provide required annotator details
- `/user`
  - Handle user registration, login and serve user details to the frontend when requested
- `**/images` (`user/images` and `annotator/images`)
  - Allow user to upload bulk images to be annotated and query annotated images, 
meanwhile allow annotators to label and annotate the user uploaded images and save it back in the database and storage
