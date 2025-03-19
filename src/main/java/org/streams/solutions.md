# Java Streams Practice Problems with Solutions

## **Level 1: Basic (Filtering & Mapping)**

### **1. Get a list of all employee names.**
```java
List<String> names = employees.stream()
        .map(SoftwareEmployee::getName)
        .collect(Collectors.toList());
```

### **2. Extract a list of all unique job titles.**
```java
Set<String> jobTitles = employees.stream()
        .map(SoftwareEmployee::getJobTitle)
        .collect(Collectors.toSet());
```

### **3. Retrieve the names of employees working at Google.**
```java
List<String> googleEmployees = employees.stream()
        .filter(e -> "Google".equals(e.getCompany()))
        .map(SoftwareEmployee::getName)
        .collect(Collectors.toList());
```

### **4. Find employees who have more than 5 years of experience.**
```java
List<SoftwareEmployee> experiencedEmployees = employees.stream()
        .filter(e -> e.getExperienceYears() > 5)
        .collect(Collectors.toList());
```

### **5. List employees whose job title contains "Engineer".**
```java
List<SoftwareEmployee> engineers = employees.stream()
        .filter(e -> e.getJobTitle().contains("Engineer"))
        .collect(Collectors.toList());
```

### **6. Get a set of all distinct companies.**
```java
Set<String> companies = employees.stream()
        .map(SoftwareEmployee::getCompany)
        .collect(Collectors.toSet());
```

### **7. Retrieve employees based in New York.**
```java
List<SoftwareEmployee> nyEmployees = employees.stream()
        .filter(e -> "New York".equals(e.getLocation()))
        .collect(Collectors.toList());
```

### **8. Find employees with at least one skill in Java.**
```java
List<SoftwareEmployee> javaExperts = employees.stream()
        .filter(e -> e.getSkills().contains("Java"))
        .collect(Collectors.toList());
```

### **9. Convert all employee names to uppercase.**
```java
List<String> upperCaseNames = employees.stream()
        .map(e -> e.getName().toUpperCase())
        .collect(Collectors.toList());
```

### **10. Check if any employee has more than 15 years of experience.**
```java
boolean hasSeniorEmployee = employees.stream()
        .anyMatch(e -> e.getExperienceYears() > 15);
```

---

# **Java Streams Practice - Level 2: Intermediate (Sorting & Reduction)**

## **11. Find the employee with the highest experience.**
```java
Optional<SoftwareEmployee> mostExperienced = employees.stream()
    .max(Comparator.comparingInt(SoftwareEmployee::getExperienceYears));
```

## **12. Get the average experience of all employees.**
```java
double avgExperience = employees.stream()
    .mapToInt(SoftwareEmployee::getExperienceYears)
    .average()
    .orElse(0);
```

## **13. Sort employees by experience in descending order.**
```java
List<SoftwareEmployee> sortedByExperience = employees.stream()
    .sorted(Comparator.comparingInt(SoftwareEmployee::getExperienceYears).reversed())
    .collect(Collectors.toList());
```

## **14. Get the employee with the highest salary (Base Salary).**
```java
Optional<SoftwareEmployee> highestPaid = employees.stream()
    .max(Comparator.comparing(e -> e.getSalaryDetails().get("Base Salary")));
```

## **15. Calculate the total combined salary of all employees.**
```java
double totalSalary = employees.stream()
    .mapToDouble(e -> e.getSalaryDetails().get("Base Salary"))
    .sum();
```

## **16. Find the most common job title among employees.**
```java
String mostCommonJobTitle = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getJobTitle, Collectors.counting()))
    .entrySet().stream()
    .max(Map.Entry.comparingByValue())
    .map(Map.Entry::getKey)
    .orElse("Unknown");
```

## **17. Retrieve the top 5 highest-paid employees.**
```java
List<SoftwareEmployee> top5HighestPaid = employees.stream()
    .sorted(Comparator.comparing(e -> e.getSalaryDetails().get("Base Salary"), Comparator.reverseOrder()))
    .limit(5)
    .collect(Collectors.toList());
```

## **18. Sort employees by name alphabetically.**
```java
List<SoftwareEmployee> sortedByName = employees.stream()
    .sorted(Comparator.comparing(SoftwareEmployee::getName))
    .collect(Collectors.toList());
```

## **19. Get the youngest employee (least experienced).**
```java
Optional<SoftwareEmployee> youngestEmployee = employees.stream()
    .min(Comparator.comparingInt(SoftwareEmployee::getExperienceYears));
```

## **20. Find if all employees have a salary greater than 50,000.**
```java
boolean allAbove50k = employees.stream()
    .allMatch(e -> e.getSalaryDetails().get("Base Salary") > 50000);
```

---
# Java Streams Practice Problems - Level 3: Advanced (Grouping & Advanced Mapping)

## **21. Group employees by job title.**
```java
Map<String, List<SoftwareEmployee>> employeesByJobTitle = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getJobTitle));
```

## **22. Count how many employees work in each company.**
```java
Map<String, Long> employeeCountByCompany = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getCompany, Collectors.counting()));
```

## **23. Find the average salary of employees in each industry.**
```java
Map<String, Double> avgSalaryByIndustry = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getIndustry,
        Collectors.averagingDouble(e -> e.getSalaryDetails().get("Base Salary"))));
```

## **24. Retrieve employees grouped by their degree.**
```java
Map<String, List<SoftwareEmployee>> employeesByDegree = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getDegree));
```

## **25. Get the highest-paid employee in each company.**
```java
Map<String, Optional<SoftwareEmployee>> highestPaidByCompany = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getCompany,
        Collectors.maxBy(Comparator.comparing(e -> e.getSalaryDetails().get("Base Salary")))));
```

## **26. Find the number of employees per location.**
```java
Map<String, Long> employeeCountByLocation = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getLocation, Collectors.counting()));
```

## **27. Get a mapping of each job title to a list of employee names.**
```java
Map<String, List<String>> jobTitleToNames = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getJobTitle,
        Collectors.mapping(SoftwareEmployee::getName, Collectors.toList())));
```

## **28. Compute the sum of experience per company.**
```java
Map<String, Integer> totalExperienceByCompany = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getCompany,
        Collectors.summingInt(SoftwareEmployee::getExperienceYears)));
```

## **29. Find the most frequently occurring skill among employees.**
```java
String mostCommonSkill = employees.stream()
    .flatMap(e -> e.getSkills().stream())
    .collect(Collectors.groupingBy(skill -> skill, Collectors.counting()))
    .entrySet().stream()
    .max(Map.Entry.comparingByValue())
    .map(Map.Entry::getKey)
    .orElse(null);
```

## **30. Get the oldest employee per industry.**
```java
Map<String, Optional<SoftwareEmployee>> oldestEmployeeByIndustry = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getIndustry,
        Collectors.maxBy(Comparator.comparingInt(SoftwareEmployee::getAge))));
```

---

# **Level 4: Expert (Complex Reductions & Parallel Streams)**

## **31. Find the employee with the most skills.**
```java
Optional<SoftwareEmployee> mostSkilled = employees.stream()
    .max(Comparator.comparingInt(e -> e.getSkills().size()));
```

## **32. Compute the average skill rating for each skill across all employees.**
```java
Map<String, Double> avgSkillRating = employees.stream()
    .flatMap(e -> e.getSkillRatings().entrySet().stream())
    .collect(Collectors.groupingBy(
        Map.Entry::getKey,
        Collectors.averagingDouble(Map.Entry::getValue)
    ));
```

## **33. Find the most common certification among employees.**
```java
String mostCommonCertification = employees.stream()
    .flatMap(e -> e.getCertifications().stream())
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
    .entrySet().stream()
    .max(Map.Entry.comparingByValue())
    .map(Map.Entry::getKey)
    .orElse(null);
```

## **34. Retrieve the employee with the highest total skill rating sum.**
```java
Optional<SoftwareEmployee> highestSkillRated = employees.stream()
    .max(Comparator.comparingInt(e -> e.getSkillRatings().values().stream().mapToInt(Integer::intValue).sum()));
```

## **35. Get the top 3 locations with the most employees.**
```java
List<String> topLocations = employees.stream()
    .collect(Collectors.groupingBy(SoftwareEmployee::getLocation, Collectors.counting()))
    .entrySet().stream()
    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
    .limit(3)
    .map(Map.Entry::getKey)
    .collect(Collectors.toList());
```

## **36. Check if there’s an employee who has all 3 cloud certifications (AWS, Azure, GCP).**
```java
boolean hasCloudExpert = employees.stream()
    .anyMatch(e -> e.getCertifications().containsAll(Arrays.asList("AWS", "Azure", "GCP")));
```

## **37. Generate a list of employees sorted by experience and salary.**
```java
List<SoftwareEmployee> sortedByExpAndSalary = employees.stream()
    .sorted(Comparator.comparingInt(SoftwareEmployee::getExperienceYears)
        .thenComparing(e -> e.getSalaryDetails().get("Base Salary"), Comparator.reverseOrder()))
    .collect(Collectors.toList());
```

## **38. Find the average years of experience per skill.**
```java
Map<String, Double> avgExperiencePerSkill = employees.stream()
    .flatMap(e -> e.getSkills().stream().map(skill -> new AbstractMap.SimpleEntry<>(skill, e.getExperienceYears())))
    .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.averagingInt(Map.Entry::getValue)));
```

## **39. Compute the total salary cost per company.**
```java
Map<String, Double> totalSalaryPerCompany = employees.stream()
    .collect(Collectors.groupingBy(
        SoftwareEmployee::getCompany,
        Collectors.summingDouble(e -> e.getSalaryDetails().get("Base Salary"))
    ));
```

## **40. Parallel process the dataset to count employees with experience greater than 10 years.**
```java
long experiencedCount = employees.parallelStream()
    .filter(e -> e.getExperienceYears() > 10)
    .count();
```

---

# **Level 5: Master (Custom Collectors & Complex Queries)**

## **41. Create a mapping of job title → average experience**
```java
Map<String, Double> jobTitleToAvgExperience = employees.stream()
    .collect(Collectors.groupingBy(
        SoftwareEmployee::getJobTitle,
        Collectors.averagingInt(SoftwareEmployee::getExperienceYears)
    ));
```

## **42. Get a breakdown of total salaries per industry**
```java
Map<String, Double> totalSalaryByIndustry = employees.stream()
    .collect(Collectors.groupingBy(
        SoftwareEmployee::getIndustry,
        Collectors.summingDouble(e -> e.getSalaryDetails().get("Base Salary"))
    ));
```

## **43. Find the employee whose skill rating sum is highest in "Machine Learning"**
```java
Optional<SoftwareEmployee> topMLExpert = employees.stream()
    .filter(e -> e.getSkills().contains("Machine Learning"))
    .max(Comparator.comparingInt(e -> e.getSkillRatings().get("Machine Learning")));
```

## **44. Compute the top 3 industries with the highest total salary payout**
```java
List<String> topIndustriesBySalary = employees.stream()
    .collect(Collectors.groupingBy(
        SoftwareEmployee::getIndustry,
        Collectors.summingDouble(e -> e.getSalaryDetails().get("Base Salary"))
    ))
    .entrySet().stream()
    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
    .limit(3)
    .map(Map.Entry::getKey)
    .collect(Collectors.toList());
```

## **45. Get the employee with the most diverse skills (widest range of skills)**
```java
Optional<SoftwareEmployee> mostVersatileEmployee = employees.stream()
    .max(Comparator.comparingInt(e -> e.getSkills().size()));
```

## **46. Find the percentage of employees in FinTech compared to all industries**
```java
double fintechPercentage = (double) employees.stream()
    .filter(e -> "FinTech".equals(e.getIndustry()))
    .count() / employees.size() * 100;
```

## **47. Compute a histogram of employees per experience level (e.g., 0-3, 3-5, 5-10, etc.)**
```java
Map<String, Long> experienceHistogram = employees.stream()
    .collect(Collectors.groupingBy(
        e -> {
            int exp = e.getExperienceYears();
            if (exp <= 3) return "0-3 years";
            else if (exp <= 5) return "3-5 years";
            else if (exp <= 10) return "5-10 years";
            else return "10+ years";
        }, Collectors.counting()
    ));
```

## **48. Find an employee who has the highest rating in Java & Spring Boot combined**
```java
Optional<SoftwareEmployee> bestJavaSpringDev = employees.stream()
    .filter(e -> e.getSkillRatings().containsKey("Java") && e.getSkillRatings().containsKey("Spring Boot"))
    .max(Comparator.comparingInt(e -> e.getSkillRatings().get("Java") + e.getSkillRatings().get("Spring Boot")));
```

## **49. Retrieve the employee with the most projects completed**
```java
Optional<SoftwareEmployee> mostProjectsEmployee = employees.stream()
    .max(Comparator.comparingInt(SoftwareEmployee::getProjectsCompleted));
```

## **50. Use a custom collector to find the top 3 skills across all employees by average rating**
```java
List<String> topSkillsByAvgRating = employees.stream()
    .flatMap(e -> e.getSkillRatings().entrySet().stream())
    .collect(Collectors.groupingBy(
        Map.Entry::getKey,
        Collectors.averagingInt(Map.Entry::getValue)
    ))
    .entrySet().stream()
    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
    .limit(3)
    .map(Map.Entry::getKey)
    .collect(Collectors.toList());
```

---