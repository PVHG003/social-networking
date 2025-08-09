import postApi from "@/api/postApi";
import { useEffect, useState, type FunctionComponent } from "react";
import { useNavigate, useParams } from "react-router-dom";

interface PostDetailsProps {}

const PostDetails: FunctionComponent<PostDetailsProps> = () => {
  const { postId } = useParams();
  const [post, setPost] = useState<any>(null);
  const [likes, setLikes] = useState<any[]>([]);
  const [comments, setComments] = useState<any[]>([]);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const navigate = useNavigate();

  const fetchAllData = async () => {
    setLoading(true);
    setError(null);
    try {
      const [postRes, likesRes, commentsRes] = await Promise.all([
        postApi.getPostById(postId!),
        postApi.getPostLikes(postId!),
        postApi.getPostComments(postId!),
      ]);

      setPost(postRes.data);
      setLikes(likesRes.data);
      setComments(commentsRes.data);
    } catch (err) {
      console.error("Error fetching post details:", err);
      setError("Failed to load post details");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (postId) {
      fetchAllData();
    } else {
      navigate("/404");
    }
  }, [postId]);

  return (
    <div className="p-4">
      {loading && <p>Loading...</p>}
      {error && <p className="text-red-500">{error}</p>}

      {post && (
        <div className="bg-white shadow-md rounded-lg p-6">
          <h2 className="text-xl font-bold mb-4">{post.content}</h2>

          {post.postMedias && post.postMedias.length > 0 && (
            <div className="grid grid-cols-2 gap-2 mb-4">
              {post.postMedias.map((media: any, idx: number) => (
                <img
                  key={idx}
                  src={`http://localhost:8080/${media.storagePath}`}
                  alt={`Post media ${idx + 1}`}
                  className="rounded"
                />
              ))}
            </div>
          )}

          <div className="mb-4">
            <strong>Likes:</strong> {likes.length}
          </div>

          <div>
            <strong>Comments:</strong> {comments.length}
            <ul className="mt-2 space-y-2">
              {comments.map((c: any) => (
                <li key={c.id} className="border-b pb-2">
                  <span className="font-semibold">{c.username}:</span>{" "}
                  {c.content}
                </li>
              ))}
            </ul>
          </div>
        </div>
      )}
    </div>
  );
};

export default PostDetails;
