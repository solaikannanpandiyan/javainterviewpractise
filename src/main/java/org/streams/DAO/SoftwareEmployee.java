package org.streams.DAO;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class SoftwareEmployee implements Serializable {

    private static final long serialVersionUID = 1L; // For Java Serialization
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private static final List<String> NAMES = List.of("Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Hannah");
    private static final List<String> JOB_TITLES = List.of("Software Engineer", "Senior Developer", "Tech Lead", "Architect", "DevOps Engineer", "Data Scientist");
    private static final List<String> COMPANIES = List.of("Google", "Amazon", "Microsoft", "Facebook", "Apple", "Netflix", "Uber");
    private static final List<Integer> EXPERIENCE_LEVELS = List.of(1, 2, 3, 5, 7, 10, 15, 20);
    private static final List<String> TECH_SKILLS = List.of("Java", "Python", "Spring Boot", "Docker", "Kubernetes", "AWS", "Microservices", "Machine Learning");
    private static final List<String> LOCATIONS = List.of("New York", "San Francisco", "Seattle", "London", "Berlin", "Toronto", "Bangalore");
    private static final List<String> DEGREES = List.of("B.Tech", "M.Tech", "B.Sc", "M.Sc", "Ph.D", "Diploma");
    private static final List<String> INDUSTRIES = List.of("FinTech", "E-Commerce", "Healthcare", "EdTech", "Cybersecurity", "AI", "Cloud Computing");
    private static final List<Double> SALARY_RANGES = List.of(60000.0, 80000.0, 100000.0, 120000.0, 150000.0, 200000.0);
    private static final List<String> CERTIFICATIONS = List.of("AWS Certified", "Google Cloud Professional", "Microsoft Azure Expert", "Certified Kubernetes Administrator");
    private static final List<String> PROJECTS = List.of("AI Chatbot", "Cloud Migration", "E-Commerce Platform", "Cybersecurity Dashboard", "Streaming Data Pipeline");

    private static final Random RANDOM = new Random();

    private final long employeeId;
    private final String name;
    private final String jobTitle;
    private final String company;
    private final int experienceYears;
    private final Set<String> skills;
    private final String location;
    private final String degree;
    private final String industry;
    private final Map<String, Double> salaryDetails;
    private final String certification;
    private final String project;
    private final Map<String, Double> skillRatings;

    public SoftwareEmployee(long employeeId, String name, String jobTitle, String company, int experienceYears, Set<String> skills, String location, String degree, String industry, Map<String, Double> salaryDetails, String certification, String project, Map<String, Double> skillRatings) {
        this.employeeId = employeeId;
        this.name = name;
        this.jobTitle = jobTitle;
        this.company = company;
        this.experienceYears = experienceYears;
        this.skills = skills;
        this.location = location;
        this.degree = degree;
        this.industry = industry;
        this.salaryDetails = salaryDetails;
        this.certification = certification;
        this.project = project;
        this.skillRatings = skillRatings;
    }

    @Override
    public String toString() {
        return String.format(
                "SoftwareEmployee{employeeId=%d, name='%s', jobTitle='%s', company='%s', experienceYears=%d, skills=%s, location='%s', degree='%s', industry='%s', salaryDetails=%s, certification='%s', project='%s', skillRatings=%s}",
                employeeId, name, jobTitle, company, experienceYears, skills, location, degree, industry, salaryDetails, certification, project, skillRatings
        );
    }

    public static SoftwareEmployee generateRandom() {
        Set<String> uniqueSkills = new HashSet<>();
        while (uniqueSkills.size() < 2) {
            uniqueSkills.add(TECH_SKILLS.get(RANDOM.nextInt(TECH_SKILLS.size())));
        }

        Map<String, Double> skillRatings = new LinkedHashMap<>();
        for (String skill : uniqueSkills) {
            skillRatings.put(skill, RANDOM.nextDouble() * 10);
        }

        return new SoftwareEmployee(
                ID_GENERATOR.getAndIncrement(),
                NAMES.get(RANDOM.nextInt(NAMES.size())),
                JOB_TITLES.get(RANDOM.nextInt(JOB_TITLES.size())),
                COMPANIES.get(RANDOM.nextInt(COMPANIES.size())),
                EXPERIENCE_LEVELS.get(RANDOM.nextInt(EXPERIENCE_LEVELS.size())),
                new HashSet<>(List.of(
                        TECH_SKILLS.get(RANDOM.nextInt(TECH_SKILLS.size())),
                        TECH_SKILLS.get(RANDOM.nextInt(TECH_SKILLS.size())),
                        TECH_SKILLS.get(RANDOM.nextInt(TECH_SKILLS.size()))
                )),
                LOCATIONS.get(RANDOM.nextInt(LOCATIONS.size())),
                DEGREES.get(RANDOM.nextInt(DEGREES.size())),
                INDUSTRIES.get(RANDOM.nextInt(INDUSTRIES.size())),
                Map.of(
                        "Base Salary", SALARY_RANGES.get(RANDOM.nextInt(SALARY_RANGES.size())),
                        "Bonus", SALARY_RANGES.get(RANDOM.nextInt(SALARY_RANGES.size())) * 0.1
                ),
                CERTIFICATIONS.get(RANDOM.nextInt(CERTIFICATIONS.size())),
                PROJECTS.get(RANDOM.nextInt(PROJECTS.size())),
                skillRatings
        );
    }
}
