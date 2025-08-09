# FollowControllerApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**follow**](#follow) | **POST** /api/users/{handleName}/follow | |
|[**followers**](#followers) | **GET** /api/users/{handleName}/followers | |
|[**followings**](#followings) | **GET** /api/users/{handleName}/followings | |
|[**unfollow**](#unfollow) | **DELETE** /api/users/{handleName}/unfollow | |

# **follow**
> ApiResponseVoid follow()


### Example

```typescript
import {
    FollowControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FollowControllerApi(configuration);

let handleName: string; // (default to undefined)

const { status, data } = await apiInstance.follow(
    handleName
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **handleName** | [**string**] |  | defaults to undefined|


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

# **followers**
> ApiPagedResponseUserProfileResponse followers()


### Example

```typescript
import {
    FollowControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FollowControllerApi(configuration);

let handleName: string; // (default to undefined)
let page: number; // (optional) (default to 1)
let size: number; // (optional) (default to 10)

const { status, data } = await apiInstance.followers(
    handleName,
    page,
    size
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **handleName** | [**string**] |  | defaults to undefined|
| **page** | [**number**] |  | (optional) defaults to 1|
| **size** | [**number**] |  | (optional) defaults to 10|


### Return type

**ApiPagedResponseUserProfileResponse**

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

# **followings**
> ApiPagedResponseUserProfileResponse followings()


### Example

```typescript
import {
    FollowControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FollowControllerApi(configuration);

let handleName: string; // (default to undefined)
let page: number; // (optional) (default to 1)
let size: number; // (optional) (default to 10)

const { status, data } = await apiInstance.followings(
    handleName,
    page,
    size
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **handleName** | [**string**] |  | defaults to undefined|
| **page** | [**number**] |  | (optional) defaults to 1|
| **size** | [**number**] |  | (optional) defaults to 10|


### Return type

**ApiPagedResponseUserProfileResponse**

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

# **unfollow**
> ApiResponseVoid unfollow()


### Example

```typescript
import {
    FollowControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new FollowControllerApi(configuration);

let handleName: string; // (default to undefined)

const { status, data } = await apiInstance.unfollow(
    handleName
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **handleName** | [**string**] |  | defaults to undefined|


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

