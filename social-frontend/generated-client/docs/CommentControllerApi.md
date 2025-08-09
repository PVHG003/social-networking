# CommentControllerApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**deleteComment**](#deletecomment) | **DELETE** /api/comments/{commentId} | |
|[**getCommentById**](#getcommentbyid) | **GET** /api/comments/{commentId} | |
|[**getPostComments**](#getpostcomments) | **GET** /api/posts/{postId}/comments | |
|[**postComment**](#postcomment) | **POST** /api/posts/{postId}/comments | |
|[**updateComment**](#updatecomment) | **PUT** /api/comments/{commentId} | |

# **deleteComment**
> ApiResponseVoid deleteComment()


### Example

```typescript
import {
    CommentControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CommentControllerApi(configuration);

let commentId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteComment(
    commentId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **commentId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseVoid**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getCommentById**
> ApiResponseCommentResponse getCommentById()


### Example

```typescript
import {
    CommentControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CommentControllerApi(configuration);

let commentId: string; // (default to undefined)

const { status, data } = await apiInstance.getCommentById(
    commentId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **commentId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseCommentResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getPostComments**
> ApiPagedResponseCommentResponse getPostComments()


### Example

```typescript
import {
    CommentControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new CommentControllerApi(configuration);

let postId: string; // (default to undefined)
let page: number; // (optional) (default to 1)
let size: number; // (optional) (default to 10)

const { status, data } = await apiInstance.getPostComments(
    postId,
    page,
    size
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **postId** | [**string**] |  | defaults to undefined|
| **page** | [**number**] |  | (optional) defaults to 1|
| **size** | [**number**] |  | (optional) defaults to 10|


### Return type

**ApiPagedResponseCommentResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **postComment**
> ApiResponseCommentResponse postComment(commentRequest)


### Example

```typescript
import {
    CommentControllerApi,
    Configuration,
    CommentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new CommentControllerApi(configuration);

let postId: string; // (default to undefined)
let commentRequest: CommentRequest; //

const { status, data } = await apiInstance.postComment(
    postId,
    commentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **commentRequest** | **CommentRequest**|  | |
| **postId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseCommentResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **updateComment**
> ApiResponseCommentResponse updateComment(commentRequest)


### Example

```typescript
import {
    CommentControllerApi,
    Configuration,
    CommentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new CommentControllerApi(configuration);

let commentId: string; // (default to undefined)
let commentRequest: CommentRequest; //

const { status, data } = await apiInstance.updateComment(
    commentId,
    commentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **commentRequest** | **CommentRequest**|  | |
| **commentId** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseCommentResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

