package com.qa.genericscripts;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Stream_methods {

	public static void main(String[] args) {
		// browser
		System.setProperty("webdriver.chrome.driver", "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// actions
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();

		// Locate all table rows (excluding the header row)
		List<WebElement> rows = driver.findElements(By.xpath("//table[@name='courses']/tbody/tr"));

		// 1. Filter rows based on a condition (e.g., column 2 contains "SpecificValue")
		List<WebElement> filteredRows = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0) // Skip
																													// empty
																													// rows
				.filter(row -> row.findElement(By.xpath("./td[2]")).getText().contains("Learn"))
				.collect(Collectors.toList());
		System.out.println("Filtered Rows Count: " + filteredRows.size() + "\n");

		// 2. Map rows to a list of column 1 values
		List<String> column1Values = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.map(row -> row.findElement(By.xpath("./td[2]")).getText()) // Extract column 2 text
				.collect(Collectors.toList());
		System.out.println("Column 2 Values: " + column1Values + "\n");

		// 3. Sort rows by column 3 (numerical values)
		List<WebElement> sortedRows = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.sorted(Comparator
						.comparingInt(row -> Integer.parseInt(row.findElement(By.xpath("./td[3]")).getText())))
				.collect(Collectors.toList());
		System.out.println("Rows sorted by Column 3:");

		sortedRows.forEach(row -> System.out.println(row.findElement(By.xpath("./td[3]")).getText()));
		System.out.println();

		// 4. Distinct values from column 3
		Set<String> distinctColumn4Values = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.map(row -> row.findElement(By.xpath("./td[3]")).getText()).collect(Collectors.toSet());
		System.out.println("Distinct Column 3 Values: " + distinctColumn4Values + "\n");

		// 5. Count rows that match a condition
		long matchingRowCount = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.filter(row -> row.findElement(By.xpath("./td[2]")).getText().contains("Learn")).count();
		System.out.println("Matching Row Count: " + matchingRowCount + "\n");

		// 6. Check if any row matches a condition
		boolean anyMatch = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.anyMatch(row -> row.findElement(By.xpath("./td[2]")).getText().contains("Learn"));
		System.out.println("Any row matches condition: " + anyMatch + "\n");

		// 7. Find the first row with a specific condition
		Optional<WebElement> firstRow = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.filter(row -> row.findElement(By.xpath("./td[2]")).getText().contains("Learn")).findFirst();
		firstRow.ifPresent(row -> System.out
				.println("First matching row column 1 value: " + row.findElement(By.xpath("./td[1]")).getText()));
		System.out.println();

		// 8. Reduce to concatenate all column 1 values into a single string
		String concatenatedColumn1Values = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.map(row -> row.findElement(By.xpath("./td[1]")).getText()).reduce("", (a, b) -> a + ", " + b);
		System.out.println("Concatenated Column 1 Values: " + concatenatedColumn1Values + "\n");

		// 9. Collect results into a Set
		Set<String> column1Set = rows.stream().filter(row -> row.findElements(By.tagName("td")).size() > 0)
				.map(row -> row.findElement(By.xpath("./td[1]")).getText()).collect(Collectors.toSet());
		System.out.println("Column 1 Values as Set: " + column1Set + "\n");

		driver.close();

	}

}
