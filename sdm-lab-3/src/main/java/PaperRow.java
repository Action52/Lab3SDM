import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;

public class PaperRow {
    public String[] authors;
    public String[] authorIds;
    public String title;
    public int year;
    public String sourceTitle;
    public String generalConferenceName;
    public String volume;
    public String issue;
    public String artNo;
    public String pageStart;
    public String pageEnd;
    public String pageCount;
    public String[] citedBy;
    public String doi;
    public String link;
    public String[] affiliations;
    public String[] authorsWithAffiliations;
    public String abstractContent;
    public String[] authorKeywords;
    public String[] indexKeywords;
    public String documentType;
    public String publicationStage;
    public String accessType;
    public String source;
    public String eid;
    public String paperType;
    public String conferenceType;
    public String leader;
    public String[] reviewers;
    public String[] reviewDecisions;
    public String[] reviewComments;
    public String[] reviewersIds;

    //Constructor with all attributes
    public PaperRow(CSVRecord record) {
        // Assuming CSV file has columns named exactly as the Paper class fields
        authors = record.get("Authors").split(",");
        authorIds = record.get("Author(s) ID").split(";");
        title = record.get("Title");
        year = Integer.parseInt(record.get("Year"));
        sourceTitle = record.get("Source title");
        generalConferenceName = record.get("General conference name");
        volume = record.get("Volume");
        issue = record.get("Issue");
        artNo = record.get("Art. No.");
        pageStart = record.get("Page start");
        pageEnd = record.get("Page end");
        pageCount = record.get("Page count");
        citedBy = record.get("Cited by").split(",");
        doi = record.get("DOI");
        link = record.get("Link");
        affiliations = record.get("Affiliations").split(",");
        authorsWithAffiliations = record.get("Authors with affiliations").split(",");
        abstractContent = record.get("Abstract");
        authorKeywords = record.get("Author Keywords").split(";");
        indexKeywords = record.get("Index Keywords").split(";");
        documentType = record.get("Document Type");
        publicationStage = record.get("Publication Stage");
        accessType = record.get("Access Type");
        source = record.get("Source");
        eid = record.get("EID");
        paperType = record.get("PaperType");
        conferenceType = record.get("Conference Type");
        leader = record.get("Leader");
        reviewers = record.get("Reviewers").split(",");
        reviewDecisions = record.get("Review_decisions").split(",");
        reviewComments = record.get("Review_comments").split(",");
        reviewersIds = record.get("Reviwers_ids").split(",");
    }
}