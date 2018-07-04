package ind.raystar.mvc.dto;

import javax.annotation.Resource;

@Resource(name = "post")
public class PostDTO {
	private int id;
	private int postNo;
	private String title;
	private String content;
	private String writer;
	private String attachment;
	private String attachmentLink;
	private int readCount;

	public int getId() {
		return id;
	}

	public int getPostNo() {
		return postNo;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getWriter() {
		return writer;
	}

	public String getAttachment() {
		return attachment;
	}

	public String getAttachmentLink() {
		return attachmentLink;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public void setAttachmentLink(String attachmentLink) {
		this.attachmentLink = attachmentLink;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

}
