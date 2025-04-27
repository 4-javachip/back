local remaining = redis.call('GET', KEYS[1])
if not remaining then
    return -1 -- 키 없음
end
if tonumber(remaining) <= 0 then
    return 0 -- 품절
else
    redis.call('DECR', KEYS[1])
    return 1 -- 다운로드 성공
end
