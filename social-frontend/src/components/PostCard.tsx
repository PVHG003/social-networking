import { useNavigate, useParams } from "react-router-dom";
import { Card, CardContent, CardHeader } from "./ui/card";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { BubblesIcon, MessageCircle, ThumbsUp } from "lucide-react";

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
  const navigate = useNavigate();

  return (
    <Card
      className="mb-4 p-4 shadow-sm hover:shadow-md transition-shadow hover:cursor-pointer hover:bg-gray-50"
      onClick={() => {
        navigate(`/posts/${post.id}`);
      }}
    >
      <CardHeader className="flex items-center gap-3">
        <Avatar className="h-10 w-10">
          <AvatarImage src={`http://localhost:8080/${post.profilePicture}`}/>
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
          <div className="mt-2 grid grid-cols-2 gap-2">
            {post.postMedias.slice(0, 4).map((media, idx) => {
              // If this is the 4th image and there are more
              if (idx === 3 && post!.postMedias!.length > 4) {
                return (
                  <div
                    key={idx}
                    className="relative mt-2 rounded overflow-hidden"
                  >
                    <img
                      src={`http://localhost:8080/${media?.storagePath}`}
                      alt={`Post media ${idx + 1}`}
                      className="w-full h-full object-cover"
                    />
                    <div className="absolute inset-0 bg-black bg-opacity-50 flex items-center justify-center">
                      <span className="text-white text-xl font-bold">
                        +{post!.postMedias!.length - 4}
                      </span>
                    </div>
                  </div>
                );
              }

              return (
                <img
                  key={idx}
                  src={`http://localhost:8080/${media?.storagePath}`}
                  alt={`Post media ${idx + 1}`}
                  className="mt-2 rounded w-full h-full object-cover"
                />
              );
            })}
          </div>
        )}

        <div className="mt-2 text-sm text-gray-500">
          Posted by @{handleName} • {new Date(post.createdAt).toLocaleString()}
        </div>
        <div className="flex gap-2 mt-1 text-sm text-gray-500">
          <div className="flex items-center gap-2">
            <ThumbsUp />
            {post.likeCounts}
          </div>
          <div className="flex items-center gap-2">
            <MessageCircle />
            {post.commentCounts}
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default PostCard;
