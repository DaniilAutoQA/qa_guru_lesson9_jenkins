package test;

import com.github.javafaker.Faker;
import components.CalendarElement;
import model.StudentModel;
import org.junit.jupiter.api.Test;
import page.FillRegistrationForm;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static components.CalendarElement.calendarComponent;
import static io.qameta.allure.Allure.step;

public class RegistrationFormPageObjectTests extends TestBase {

    Faker faker = new Faker();
    FillRegistrationForm registration = new FillRegistrationForm();
    CalendarElement calendar = new CalendarElement("1", "March", "2000");
    StudentModel student = new StudentModel(
            faker.name().firstName(),
            faker.name().lastName(),
            faker.internet().emailAddress(),
            "Male",
            faker.phoneNumber().subscriberNumber(10),
            "Sports",
            "NCR", "Delhi",
            "English",
            faker.address().fullAddress(),
            "load.txt");

    @Test
    void fillFormTest() {
        step("Open Student Registration Form", () -> {
            open("https://demoqa.com/automation-practice-form");
        });
        step("Fill registration form", () -> {
            step("Fill common data", () -> {
                registration.inputFirstName(student.getFirstName());
                registration.inputLastName(student.getLastName());
                registration.inputEmail(student.getEmail());
                registration.chooseGender(student.getGender());
                registration.inputMobile(student.getMobile());
            });
            step("set DoB", () -> {
                calendarComponent(calendar.getDay(), calendar.getMonth(), calendar.getYear());
            });
            step("upload file", () -> {
                registration.uploadFile(student.getFileName());
            });
            step("Set address and hobby", () -> {
                registration.currentAddress(student.getCurrentAddress());
                registration.chooseStateAndCity(student.getState(), student.getCity());
                registration.chooseHobby(student.getHobbies());
                registration.chooseSubject(student.getSubjects());
            });
        });
        step("Submit form", () -> {
            registration.submit();
        });


        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()= 'Student Name']").parent().shouldHave(text(student.getFirstName() + " " + student.getLastName()));
        $x("//td[text()='Student Email']").parent().shouldHave(text(student.getEmail()));
        $x("//td[text()='Gender']").parent().shouldHave(text(student.getGender()));
        $x("//td[text()='Mobile']").parent().shouldHave(text(student.getMobile()));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(calendar.getDay() + " " + calendar.getMonth() + "," + calendar.getYear()));
        $x("//td[text()='Subjects']").parent().shouldHave(text(student.getSubjects()));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(student.getHobbies()));
        $x("//td[text()='Picture']").parent().shouldHave(text(student.getFileName()));
        $x("//td[text()='Address']").parent().shouldHave(text(student.getCurrentAddress()));
        $x("//td[text()='State and City']").parent().shouldHave(text(student.getState() + " " + student.getCity()));

    }
}
