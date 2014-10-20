package socialnetwork.model.dao;

import java.util.List;

import socialnetwork.model.dao.bean.Tag;

public interface TagDao {
	
	public List<Tag> getAllTags();
	public Tag getTagById(final Long id);
	public void addTag(final Tag tag);
	public void updateTag(final Tag tag);
	public void addOrUpdateTag(final Tag tag);
	public void deleteTag(final Long id);

}
