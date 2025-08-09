import { useParams } from "react-router-dom";
import { Card, CardContent, CardHeader } from "./ui/card";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";

interface PostDetails {
  id: string;
  username: string;
  profilePicture: string | null;
  content: string | null;
  postMedias: string[] | null;
  likeCounts: number;
  commentCounts: number;
  createdAt: string;
  updatedAt: string;
}

const PostCard = ({ post }: { post: PostDetails }) => {
  const { handleName } = useParams();

  return (
    <Card className="mb-4 p-4 shadow-sm hover:shadow-md transition-shadow">
      <CardHeader className="flex items-center gap-3">
        <Avatar>
          <AvatarImage src={`http://localhost:8080/${post.profilePicture}`} />
          <AvatarFallback>{post.username.charAt(0)}</AvatarFallback>
        </Avatar>
        <div className="ml-3">
          <div className="font-semibold">{post.username}</div>
          <div className="text-sm text-gray-500">@{handleName}</div>
        </div>
      </CardHeader>
      <CardContent>
        <p className="text-gray-700">
          {post.content || "No content available"}
        </p>

        {post.postMedias && post.postMedias.length > 0 && (
          <div className="mt-2">
            {post.postMedias.map((media, idx) => (
              <img
                key={idx}
                src={`http://localhost:8080/${media?.storagePath}`}
                alt={`Post media ${idx + 1}`}
                className="mt-2 rounded"
              />
            ))}
          </div>
        )}

        <div className="mt-2 text-sm text-gray-500">
          Posted by @{handleName} • {new Date(post.createdAt).toLocaleString()}
        </div>
        <div className="mt-1 text-sm text-gray-500">
          ❤️ {post.likeCounts} • 💬 {post.commentCounts}
        </div>
      </CardContent>
    </Card>
  );
};

export default PostCard;
